package com.tothenew

import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility

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

    @Override
    String toString(){
        return "TopicName: ${name}"
    }

    def afterInsert() {
        log.info "----------Into After Insert------"
        Topic.withNewSession {
            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            if (subscription.save(flush: true)) {
                log.info "Subscription ${subscription} saved successfully"
            } else {
                log.error "Error saving subscription : ${subscription.errors.allErrors}"
            }
        }
    }
}
