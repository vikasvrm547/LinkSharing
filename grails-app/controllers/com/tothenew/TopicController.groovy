package com.tothenew

import com.tothenew.enums.Visibility

class TopicController {

    def show(Integer id) {
        log.info("showwwwwww")
        Topic topic = Topic.get(id)
        log.info topic.visibility
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC) {
                log.info "visibility"
                render("success")
            } else if (topic.visibility == Visibility.PRIVATE) {
                if (Subscription.findByUserAndTopic(session.user, topic)) {
                    render("success")
                } else {
                    flash.error = "Without subscription user cannot see private topics"
                    redirect(controller: "login", action: "index")
                }
            }
        } else {
            flash.error = "There is no such topic available"
            redirect(controller: "login", action: "index")
        }
    }
}
