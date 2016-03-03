package com.tothenew

import com.tothenew.enums.Seriousness

class SubscriptionController {

    def delete(Long id) {
        Subscription subscription = Subscription.get(id);
        if (subscription) {
            subscription.delete(flush: true)
            render("Subscription successfully deleted")
        } else {
            render("Subscription not found")
        }
    }

    def save(Long id) {
        Topic topic = Topic.get(id)
        if (topic) {
            Subscription subscription = new Subscription(topic: topic, user: session.user, seriousness: Seriousness.SERIOUS)
            if (subscription.validate()) {
                subscription.save();
                render("Subscription save successfully")
            } else {
                flash.error = "Subscription not save successfully"
                render(subscription.errors.allErrors)
            }
        } else {
            flash.error = "topic not found to save subscription"
            render("topic not found to save subscription")
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
}
