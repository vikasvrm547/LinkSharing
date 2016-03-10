package com.tothenew

import com.tothenew.enums.Seriousness
import grails.converters.JSON

class SubscriptionController {

    def delete(Long topicId) {
        Thread.sleep(2000)
        Map resultInfo = [:]
        User currentUser = session.user
        Topic topic = Topic.get(topicId)
        if (topic.createdBy.equals(session.user)){
            resultInfo.error = "Creator of topic cannot delete subscription"
        }
        else if (topic && currentUser) {
            Subscription subscription = Subscription.findByTopicAndUser(topic, currentUser)

            if (subscription) {
                subscription.delete(flush: true)
                resultInfo.message = "Subscription successfully deleted"
            } else {
                resultInfo.error = "Subscription not found"
            }
        } else {
            resultInfo.error = "topic not found to save subscription"
        }
        render(resultInfo as JSON)
    }

    def save(Long topicId) {
        Thread.sleep(2000)
        Topic topic = Topic.get(topicId)
        Map resultInfo = [:]
        if (topic) {
            Subscription subscription = new Subscription(topic: topic, user: session.user, seriousness: Seriousness.SERIOUS)
            if (subscription?.save(flush: true)) {
                resultInfo.message = "Subscription save successfully"
            } else {
                resultInfo.error = "Subscription not save successfully"
            }
        } else {

            resultInfo.error = "topic not found to save subscription"
        }
        render(resultInfo as JSON)
    }

    def update(Long topicId, String seriousnessString) {
        Map resultInfo = [:]
        Seriousness seriousnessEnum = Seriousness.convertToEnum(seriousnessString);
        Subscription subscription = Subscription.createCriteria().get {
            eq('topic.id',topicId)
            eq('user.id',session.user.id)
        }
        if (seriousnessEnum) {
            if (subscription) {
                subscription.seriousness = seriousnessEnum
                if (subscription.save(flush: true)) {
                    resultInfo.message = "Subscription updated successfully"
                } else { // never come in that case
                    resultInfo.error = "Subscription couldn't be able to save"
                }
            } else {
                resultInfo.error = "Subscription not found"
            }
        } else {
            resultInfo.error = "Seriousness not valid"
        }
        render( resultInfo as JSON)
    }

    def demo() {
        Map m = ["1": "one", "2": "two"]

        render(m as JSON)
    }
}
