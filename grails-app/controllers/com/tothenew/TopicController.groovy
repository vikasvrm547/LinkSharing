package com.tothenew

import com.tothenew.co.ResourceSearchCo
import com.tothenew.enums.Visibility
import com.tothenew.vo.TopicVO

class TopicController {

    def show(ResourceSearchCo resourceSearchCo) {
        println resourceSearchCo.topicId
        Topic topic = Topic.read(resourceSearchCo.topicId)
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC) {
                render(view: 'show', model: [subscribedUsers: topic.getSubscribedUsers(), topic: topic])
            } else if (topic.visibility == Visibility.PRIVATE) {
                if (Subscription.findByUserAndTopic(session.user, topic)) {
                    render(view: 'show', model: [subscribedUsers: topic.getSubscribedUsers(), topic: topic])
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

    def save(String topicName, String visibilityString) {
        Topic topic = new Topic(name: topicName, visibility: Visibility.convertToEnum(visibilityString), createdBy: session.user)
        if (topic.save(flush: true)) {
            flash.message = "Topic created successfully"
            redirect(controller: "user", action: "show")
        } else {
            log.error("Topic not created successfully")
            flash.error = "Topic not created successfully"
            render(topic.errors.allErrors)
        }
    }

    def displayTrendingTopics() {
        render(Topic.getTrendingTopics())
    }
}
