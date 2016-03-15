package com.tothenew

import com.tothenew.co.SearchCO
import com.tothenew.co.UserSearchCO
import com.tothenew.vo.PostVO


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

    static namedQueries = {
        search { UserSearchCO userSearchCO ->
            eq('admin', false)
            if (userSearchCO.active != null) {
                eq("active", userSearchCO.active)
            }

            if (userSearchCO.q) {
                or {
                    ilike("firstName", "%${userSearchCO.q}%")
                    ilike("lastName", "%${userSearchCO.q}%")
                    ilike("email", "%${userSearchCO.q}%")
                    ilike("userName", "%${userSearchCO.q}%")

                }
            }
        }
    }

    String getName() {
        return [firstName, lastName].findAll { it }.join(" ")
    }

   /* boolean equals(User user) {
        return this.id == user.id
    }*/

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        User user = (User) o

        if (id != user.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }

    @Override
    String toString() {
        return userName
    }

    List getSubscribedTopics() {
        List<Topic> topics = Subscription.createCriteria().list() {
            projections {
                property('topic')
            }
            eq('user', this)
        }
        return topics
    }

    Subscription getSubscription(Long topicId) {
        return Subscription.createCriteria().get {
            eq('topic.id', topicId)
            eq('user.id', this.id)
        }
    }


    Integer getTotalReadingItem() {
        return ReadingItem.countByUser(this) ?: 0
    }

    List<PostVO> getInboxItems(SearchCO searchCO) {
        List<PostVO> readingItemsList = [];
        ReadingItem.findAllByUser(this, [max: searchCO.max, offset: searchCO.offset, sort: 'lastUpdated', order: 'desc']).each {
            readingItemsList.add(new PostVO(topicId: it.resource.topic.id, resourceID: it.resource.id, description: it.resource.description,
                    topicName: it.resource.topic.name, userId: it.resource.createdBy.id, userUserName: it.resource.createdBy.userName,
                    userFirstName: it.resource.createdBy.firstName, userLastName: it.resource.createdBy.lastName,
                    isRead: it.isRead,
                    url: it.resource.class.toString().equals("class com.tothenew.LinkResource") ? it.resource.toString() : "",
                    filePath: it.resource.class.toString().equals("class com.tothenew.DocumentResource") ? it.resource.toString() : ""))

        }
        return readingItemsList
    }

    Boolean canDeleteResource(Long id) {
        Resource resource = Resource.get(id)
        if (this.admin || this.equals(resource.createdBy)) {
            return true
        } else {
            return false
        }
    }

    Boolean hasTopicRight(Long id) {
        Topic topic = Topic.get(id)
        if (this.admin || this.equals(topic.createdBy)) {
            return true
        } else {
            return false
        }
    }

    Boolean isSubscribed(Long topicId) {
        Topic topic = Topic.get(topicId)
        if (topic && Subscription.countByUserAndTopic(this, topic)) {
            return true
        } else {
            return false
        }
    }


    Integer getScore(Long resourceId) {
        Resource resource = Resource.get(resourceId)
        if (resource) {
            Integer score = ResourceRating.createCriteria().get {
                projections {
                    property('score')
                }
                eq('user', this)
                eq('resource', resource)
            }
            return score
        } else {
            return 0;
        }
    }

    static Integer updatePassword(String newPassword, String email) {
        return executeUpdate("update User set password=:newPassword where email=:email",
                [newPassword: newPassword, email: email])
    }

    List<Resource> unreadResources() {
        return ReadingItem.createCriteria().list {
            projections {
                property('resource')
            }
            eq('user', this)
            eq('isRead', false)
        }
    }
}
