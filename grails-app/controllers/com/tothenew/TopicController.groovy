package com.tothenew

import com.tothenew.co.InvitationCO
import com.tothenew.co.ResourceSearchCO
import com.tothenew.co.TopicCO
import com.tothenew.dto.EmailDTO
import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility
import com.tothenew.vo.TopicVO
import grails.converters.JSON

class TopicController {
    def emailService

    def show(ResourceSearchCO resourceSearchCO) {
        Topic topic = Topic.read(resourceSearchCO.topicId)
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC) {
                render(view: 'show', model: [subscribedUsers: topic?.getSubscribedUsers(), topic: topic,
                                             currentUser    : session.user, topicPosts: topic?.getTopicPosts()])
            } else {
                if (Subscription.countByUserAndTopic(session.user, topic)) {
                    render(view: 'show', model: [subscribedUsers: topic?.getSubscribedUsers(), topic: topic,
                                                 currentUser    : session.user, topicPosts: topic?.getTopicPosts()])
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

    def save(TopicCO topicCO) {
        Map resultInfo = [:]

        Topic topic = Topic.findOrCreateByNameAndCreatedBy(topicCO.topicName, session.user)
        if (topicCO.topicUpdatedName) {
            topic.name = topicCO.topicUpdatedName
        }
        if (topicCO.visibilityString) {
            topic.visibility = Visibility.convertToEnum(topicCO.visibilityString)
        }
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

    def invite(InvitationCO invitationCO) {
        Topic topic = Topic.get(invitationCO.topicId)
        if (topic && (!invitationCO.email.matches("\\s"))) {
            TopicVO topicVO = new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility,
                    createdBy: topic.createdBy)
            EmailDTO emailDTO = new EmailDTO(to: [invitationCO.email], subject: "Invitations for topic from linksharing",
                    view: '/email/_invite', model: [currentUser: session.user, topic: topicVO])
            emailService.sendMail(emailDTO)
            flash.message = "Successfully send invitation"
        } else {
            flash.error = "Can't sent invitation"
        }
        redirect(url: request.getHeader("referer"))
    }

    def join(Long topicId) {
        User user = session.user
        if (user) {
            Topic topic = Topic.get(topicId)
            if (topic) {
                Subscription subscription = new Subscription(topic: topic, user: session.user, seriousness: Seriousness.SERIOUS)
                if (subscription?.save(flush: true)) {
                    flash.message = "Subscription save successfully"
                } else {
                    flash.error = "Subscription not save successfully"
                }
            } else {

                flash.error = "Topic not exist"
            }
        }
        redirect(controller: "login", action: 'index')
    }

}
