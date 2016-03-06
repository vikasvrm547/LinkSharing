package com.tothenew

import com.tothenew.vo.PostVO
import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.Top

import javax.websocket.Session

class User {
    String email
    String userName
    String password
    String firstName
    String lastName
    Byte[] photo
    Boolean admin = false
    Boolean active = false
    Date dateCreated
    Date lastUpdated

    String confirmPassword;
    static transients = ['name', 'confirmPassword', 'subscribedTopics']

    static mapping = {
        photo(sqlType: 'longblob')
        sort id: 'desc'
    }

    static constraints = {
        email(unique: true, blank: false, email: true)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        userName(unique: true, blank: false)
        photo(nullable: true)
        active(nullable: true)
        admin(nullable: true)
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, user ->
            return (val != null) && val.equals(user.password)

        })
    }

    static hasMany = [topics   : Topic, subscriptions: Subscription, readingItems: ReadingItem,
                      resources: Resource, resourceRatings: ResourceRating]

    String getName() {
        return [firstName, lastName].findAll { it }.join(" ")
    }

    @Override
    String toString() {
        return userName
    }

    def getSubscribedTopics() {
        List<Topic> topics = Subscription.createCriteria().list {
            projections {
                property('topic')
            }
            eq('user', this)
        }
        return topics
    }

    def getInboxItems() {
        // User currentUser = session.user
        List<PostVO> readingItemsList = [];
        ReadingItem.findAllByUserAndIsRead(this, false).each {
            readingItemsList.add(new PostVO(resourceID: it.resource.id, description: it.resource.description,
                    topicName: it.resource.topic.name,userId: it.user.id, userUserName: it.resource.createdBy.userName,
                    userFirstName: it.resource.createdBy.firstName, userLastName: it.resource.createdBy.lastName,
                    userPhoto: it.resource.createdBy.photo, isRead: it.isRead,
                    url: it.resource.class.toString().equals("class com.tothenew.LinkResource") ? it.resource.toString() : "",
                    filePath: it.resource.class.toString().equals("class com.tothenew.DocumentResource") ? it.resource.toString() : ""))

        }
        return readingItemsList
    }


    Boolean canDeleteResource(Long id) {
        Resource resource = Resource.get(id)
        if (this.admin || (resource.createdBy.id == this.id)) {
            return true
        } else {
            return false
        }
    }

    Boolean isSubscribed(Long topicId) {
        Topic topic = Topic.get(topicId)
        if (topic && Subscription.countByUserAndTopic(this, topic)){
            return true
        }else {
            return false
        }
    }
    Integer getScore(Long resourceId){
        Resource resource = Resource.get(resourceId)
        if(resource) {
            Integer score = ResourceRating.createCriteria().get {
                projections {
                    property('score')
                }
                eq('user', this)
                eq('resource', Resource.get(8))
            }
            return score
        }else{
            return 0;
        }
    }

}
