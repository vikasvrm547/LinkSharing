package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.enums.Visibility
import grails.converters.JSON

class TopicController {

    def show(ResourceSearchCO resourceSearchCO) {
        //Topic.get(1)
        Topic topic = Topic.read(resourceSearchCO.topicId)
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC) {
                render(view: 'show', model: [subscribedUsers: topic.getSubscribedUsers(), topic: topic, currentUser: session.user,topicPosts:topic.getTopicPosts()])
                // render("Success")
            } else {
                if (Subscription.countByUserAndTopic(session.user, topic)) {
                    render(view: 'show', model: [subscribedUsers: topic.getSubscribedUsers(), topic: topic, currentUser: session.user,topicPosts:topic.getTopicPosts()])
                    // render("Success")
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
        Map resultInfo = [:]
        Topic topic = Topic.findOrCreateByNameAndCreatedBy(topicName, session.user)
        topic.visibility = Visibility.convertToEnum(visibilityString);
        if (topic.save(flush: true)) {
            resultInfo.message = "Topic saved/updated successfully"
        } else {
            resultInfo.error = "Topic not saved/update successfully"
        }
        render(resultInfo as JSON)
    }

    def delete(Long topicId) {
        Topic topic = Topic.get(topicId)
        User user = session.user
        if (topic && user && user.hasTopicRight(topicId)) {
            topic.delete(flush: true)
            flash.message = "Successfully topic delete"
        } else {
            flash.error = "Topic not found"
        }
        redirect(controller: 'login', action: 'index')
    }

}
