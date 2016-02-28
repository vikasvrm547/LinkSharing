package com.tothenew

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
    static transients = ['name', 'confirmPassword','subscribedTopics']

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

    def getSubscribedTopics(){
        List<Topic> topics = Subscription.createCriteria().list{
            projections {
                property('topic')
            }
            eq('user',this)
        }

        return  topics
    }
}
