package com.tothenew

import com.tothenew.enums.Seriousness

class SubscriptionController {

    def delete(Long subscriptionId) {
        Subscription subscription = Subscription.get(subscriptionId);
        if (subscription) {
            subscription.delete(flush: true)
            flash.message = "Subscription successfully deleted"
            redirect(controller: 'login', action: 'index')
        } else {
            flash.error = "Subscription not found"
            redirect(controller: 'login', action: 'index')

        }
    }

    def save(Long topicId) {
        Topic topic = Topic.get(topicId)
        if (topic) {
            Subscription subscription = new Subscription(topic: topic, user: session.user, seriousness: Seriousness.SERIOUS)
            if (subscription.validate()) {
                subscription.save();
                flash.message = "Subscription save successfully"
                redirect(controller: 'login', action: 'index')

            } else {
                flash.error = "Subscription not save successfully"
                redirect(controller: 'login', action: 'index')
            }
        } else {
            flash.error = "topic not found to save subscription"
            redirect(controller: 'login', action: 'index')
        }
    }

    def update(Long id, String seriousnessString) {
        Seriousness seriousnessEnum = Seriousness.convertToEnum(seriousnessString);
        Subscription subscription = Subscription.get(id)
        if (seriousnessEnum) {
            if (subscription) {
                subscription.seriousness = seriousnessEnum
                if (subscription.save(flush: true)) {
                    render "Subscription updated successfully"
                } else { // never come in that case
                    render("Subscription couldn't be able to save")
                }
            } else {
                render("Subscription not found")
            }
        } else {
            render("Seriousness not valid")
        }
    }

    def demo() {
        render(params)
    }
}
