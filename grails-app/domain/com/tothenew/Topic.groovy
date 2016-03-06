package com.tothenew

import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility
import com.tothenew.vo.PostVO
import com.tothenew.vo.TopicVO

class Topic {
    String name
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static constraints = {
        name(blank: false, unique: 'createdBy')
        visibility(inList: Visibility.values() as List)
    }

    static hasMany = [subscriptions: Subscription, resources: Resource]

    static mapping = {
        sort name: 'asc'
    }
    static transients = ['subscribedUsers']

    static namedQueries = {
        getAllTopicIDAndName {
            projections {
                property('id')
                property('name')
            }
        }
    }

    @Override
    String toString() {
        return name
    }

    def afterInsert() {
        Topic.withNewSession {
            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            if (subscription.save(flush: true)) {
                log.info "Subscription ${subscription} saved successfully"
            } else {
                log.error "Error saving subscription : ${subscription.errors.allErrors}"
            }
        }
    }

    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> topicVOList = []
        def criteria = Resource.createCriteria()
        List result = criteria.list(max: 5) {
            projections {
                createAlias('topic', 't')
                property('t.id', 'topicId')
                property('t.name', 'topicName')
                property('t.visibility', 'topicVisibility')
                rowCount('resource_count')
                property('t.createdBy')
            }

            groupProperty('t.id')
            eq('t.visibility', Visibility.PUBLIC)
            order('resource_count', 'desc')
            order('topicName', 'desc')
        }

        result.each {
            topicVOList.add(new TopicVO(id: it[0], name: it[1], visibility: it[2], count: it[3], createdBy: it[4]))
        }
        return topicVOList
    }

    List<User> getSubscribedUsers() {
        return Subscription.createCriteria().list {
            projections {
                property('user')
            }
            eq('topic', this)
        }
    }
  /*  def getTopicPosts(Long  topicId) { in complete
        // User currentUser = session.user
        List<PostVO> posts = [];
        Topic topic = Topic.get(topicId)
        if(topic){

            topic.resources.each{ posts.add(new PostVO(resourceID: it.id, description: it.description,
                    topicName: topic.name,userId: topic.createdBy.id, userUserName: topic.createdBy.userName,
                    userFirstName: topic.createdBy.firstName, userLastName: topic.createdBy.lastName,
                    userPhoto: topic.createdBy.photo, isRead: it.isRead,
                    url: it.resource.class.toString().equals("class com.tothenew.LinkResource") ? it.resource.toString() : "",
                    filePath: it.resource.class.toString().equals("class com.tothenew.DocumentResource") ? it.resource.toString() : ""))


            }
        }

        return posts
    }*/

    Boolean isPublic() {

        if (this.visibility.equals(Visibility.PUBLIC)) {
            return true
        } else {
            return false
        }
    }

    Boolean canViewedBy(User currentUser) {
        if (isPublic() || currentUser.isSubscribed(this.id) || currentUser.admin) {
            return true
        } else {
            return false
        }
    }
}
