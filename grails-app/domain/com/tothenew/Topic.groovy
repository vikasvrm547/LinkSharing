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

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Topic topic = (Topic) o

        if (id != topic.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
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

    public List<PostVO> getTopicPosts() {
        List<PostVO> topicPosts = []
        Resource.createCriteria().list() {
            projections {
                property('id')
                property('description')
                property('url')
                property('filePath')
                topic {
                    property('id')
                    property('name')
                }
                createdBy {
                    property('id')
                    property('username')
                    property('firstName')
                    property('lastName')
                }
                property('lastUpdated')
            }
            eq('topic.id', this.id)
            order('lastUpdated', 'desc')
        }.each {
            topicPosts.add(new PostVO(resourceID: it[0], description: it[1], url: it[2], filePath: it[3], topicId:
                    it[4], topicName: it[5], userId: it[6], userUserName: it[7], userFirstName: it[8], userLastName: it[9],
                    lastUpdated: it[10], isRead: ReadingItem.getIsRead(it[0], it[6])))
        }

        return topicPosts
    }

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
