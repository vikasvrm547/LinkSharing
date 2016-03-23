package com.tothenew

import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility

class LinkSharingTagLib {
    def springSecurityService
    static namespace = "ls"

    def readLink = { attr, body ->
        User user = attr.user
        Resource resource = Resource.get(attr.resourceId)
        ReadingItem readingItem = ReadingItem.findByResourceAndUser(resource, user)
        String link = "${g.createLink([controller: 'ReadingItem', action: 'changeIsRead', params: [id: attr.resourceId, isRead: !attr.isRead]])}"
        Boolean isRead = Boolean.valueOf(attr.isRead)
        if (user && readingItem)
            if (isRead == true) {
                out << "<a href=${link}>Mark as unread</a>"
            } else if (isRead == false) {
                out << "<a href=${link}>Mark as read</a>"
            }
    }

    def topPosts = { attr, body ->
        List topPostList = attr.topPosts
        out << g.render(template: "/resource/topPost", model: [topPosts: topPostList])
    }
    def trendingTopics = { attr, body ->
        List trendingTopicList = attr.tendingTopics
        out << g.render(template: "/topic/tendingTopic", model: [tendingTopics: trendingTopicList])
    }
    def resourceTypeLink = { attr, body ->
        Long resourceId = attr.resourceId
        String resourceType = Resource.checkResourceType(resourceId)
        if (resourceType?.equals("LinkResource")) {
            out << g.link([url: attr.url, target: '_blank'], "view full site")
        } else if (resourceType?.equals("DocumentResource")) {
            // condition because in case of resourceType =null elseif still not execute
            out << g.link([controller: 'DocumentResource', action: 'download', params: [resourceId: resourceId]], "Download")
        }
    }

    def canDeleteResource = { attr, body ->

        Boolean canDelete = attr.currentUser?.canDeleteResource(attr.resourceId)
        if (canDelete) {
            out << g.link([class: attr.class, controller: 'resource', action: 'delete', params: [id: "${attr.resourceId}"]], body())
        }
    }
    def canEditResource = { attr, body ->

        Boolean canEdit = attr.currentUser?.canDeleteResource(attr.resourceId)
        if (canEdit) {
            out << body()
        }
    }

    def canUpdateTopic = { attr, body ->
        Boolean canDelete = attr.currentUser?.hasTopicRight(attr.topicId)
        if (canDelete) {
            out << body()
        }
    }


    def showSubscribe = { attr, body ->
        Long topicId = attr.topicId
        User currentUser = springSecurityService.getCurrentUser()
        if (currentUser) {
            if (currentUser?.isSubscribed(topicId)) {

                out << g.link([class : "subscription ${attr.class}", controller: 'subscription', action: 'delete',
                               params: [topicId: topicId]], "Unsubscribe")
            } else {
                out << g.link([class : "subscription", controller: 'subscription', action: 'save',
                               params: [topicId: topicId]], "Subscribe")
            }
        }
    }

    def showSeriousness = { attr, body ->
        Long topicId = attr.topicId
        User currentUser = springSecurityService.getCurrentUser()
        if (currentUser && topicId) {
            Subscription subscription = currentUser?.getSubscription(topicId)
            if (subscription) {
                out << g.select(from: Seriousness.values(), optionKey: "key", value: subscription?.seriousness,
                        name: "seriousness", class: attr.class, topicId: topicId)
            }
        }
    }

    def showInvitation = { attr, body ->
        Long topicId = attr.topicId
        User currentUser = springSecurityService.getCurrentUser()
        if (currentUser && topicId) {
            Subscription subscription = currentUser?.getSubscription(topicId)
            if (subscription) {
                out << g.link(class: attr.class,title: attr.title,style: "font-size: large")
            }
        }
    }

    def showSubscribedTopics = { attr, body ->
        User currentUser = springSecurityService.getCurrentUser()
        if (currentUser) {
            out << g.select(from: currentUser?.getSubscribedTopics(),
                    name: "topicId", optionKey: "id", class: attr.class)
        }

    }

    def showVisibility = { attr, body ->
        out << g.select(from: Visibility.values(), optionKey: "key", value: attr.visibility,
                name: "visibility", class: attr.class, topicName: attr.topicName, topicId: attr.topicId)

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
        Long count = 0
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
        String url = g.createLink(controller: 'user', action: 'image', params: [userId: "${attr.userId}"])
        out << g.img([uri: url, class: "${attr.class}", height: attr.height, width: attr.width])
    }

    def canSeeCreateTopicHeaderIcon = { attr, body ->
        if(controllerName.equals("user")&& (actionName.equals("show")||actionName.equals("list"))){
            out<< body()
        }
    }
    def canSeeInviteHeaderIcon = { attr, body ->
        if(controllerName.equals("user")&& (actionName.equals("show")||actionName.equals("list"))){
            out<< body()
        }
    }

}
