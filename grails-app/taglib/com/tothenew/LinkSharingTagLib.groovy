package com.tothenew

class LinkSharingTagLib {
    static namespace = "ls"

    def readLink = { attr, body ->
        User user = attr.user
        String link = "${g.createLink([controller: 'ReadingItem', action: 'changeIsRead', params: [id: attr.resourceId, isRead: !attr.isRead]])}"
        Boolean isRead = Boolean.valueOf(attr.isRead)
        if (user)
            if (isRead) {
                out << "<a href=${link}>Mark as unread</a>"
            } else {
                out << "<a href=${link}>Mark as read</a>"
            }
    }

    def topPosts = { attr, body ->
        List topPostList = attr.topPosts;
        out << g.render(template: "/resource/topPost", model: [topPosts: topPostList])
    }
    def trendingTopics = { attr, body ->
        List trendingTopicList = attr.tendingTopics;
        out << g.render(template: "/topic/tendingTopic", model: [tendingTopics: trendingTopicList])
    }
    def resourceTypeLink = { attr, body ->
        Long resourceId = attr.resourceId
        String resourceType = Resource.checkResourceType(resourceId)
        if (resourceType?.equals("LinkResource")) {
            out << g.link([url: attr.url, target: '_blank'], "view full site")
        } else if (resourceType?.equals("DocumentResource")) {
            // condition because in case of resourceType =null elseif still not execute
            out << g.link([controller: 'DocumentResource', action: 'download' , params: [resourceId: resourceId]], "Download")
        }
    }

    def canDeleteResource = { attr, body ->

        Boolean canDelete = attr.currentUser?.canDeleteResource(attr.resourceId)
        if (canDelete) {
            out << g.link([class: attr.class, controller: 'resource', action: 'delete', params: [id: "${attr.resourceId}"]], body())
        }
    }
    def showSubscribe = { attr, body ->
        Long topicId = attr.topicId
        if (session.user) {
            if (session.user.isSubscribed(topicId)) {

                Long subscriptionId = Subscription.createCriteria().get {

                    projections {
                        property('id')
                    }
                    eq('topic.id', topicId)
                    eq('user', session.user)
                }
                out << g.link([class : attr.class, controller: 'subscription', action: 'delete',
                               params: [subscriptionId: subscriptionId]], "Unsubscribe")
            } else {
                out << g.link([class : attr.class, controller: 'subscription', action: 'save',
                               params: [topicId: topicId]], "Subscribe")
            }
        }
    }

    def resourceCount = { attr, body ->
        Long resourceCount = Resource.createCriteria().count {
            eq('topic.id', attr.topicId)
        }
        out << resourceCount ?: 0
    }

    def topicCount = { attr, body ->
        Long topicCount = Topic.createCriteria().count {
            eq('createdBy', attr.user)
        }
        out << topicCount ?: 0
    }

    def subscriptionCount = { attr, body ->
        Long count = 0;
        if (attr.topicId) {
            count = Subscription.createCriteria().count {
                eq('topic.id', attr.topicId)
            }
        } else if (attr.user) {
            count = Subscription.createCriteria().count {
                eq('user', attr.user)
            }
        }
        out << count ?: 0
    }
    def userImage = { attr, body ->
        out << "<img src='/assets/user/user.png' class = '${attr.class}' height='${attr.height}' width='${attr.width}'/>"
    }

}
