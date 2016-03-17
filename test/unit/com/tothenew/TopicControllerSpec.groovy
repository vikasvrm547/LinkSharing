package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.co.TopicCO
import com.tothenew.enums.Visibility
import grails.converters.JSON
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges


@Mock([Subscription, Topic])

@TestFor(TopicController)
class TopicControllerSpec extends Specification {

    @ConfineMetaClassChanges(Subscription)
    def "check show action with error conditions"() {
        given:
        ResourceSearchCO resourceSearchCo = new ResourceSearchCO([topicId: 10l])
        when:
        controller.show(resourceSearchCo)
        then:
        flash.error == "There is no such topic available"
        response.redirectedUrl == "/login/index"
    }

    def "check show action with visibility public"() {
        given:
        Topic topic = new Topic(createdBy: new User(), name: "t", visibility: Visibility.PUBLIC).save()
        ResourceSearchCO resourceSearchCo = new ResourceSearchCO([topicId: 1l])
        session.user = new User()
        and:
        topic.metaClass.getSubscribedUsers = {[""]}
        topic.metaClass.getTopicPosts = {[""]}
        when:
        controller.show(resourceSearchCo)
        then:
        model.currentUser != null
        model.topic != null
        model.topicPosts.size() != 0
        model.subscribedUsers.size() != 0
    }

    def "check show action with 1 or more countByUserAndTopic"() {
        given:
        Topic topic = new Topic(createdBy: new User(), name: "t", visibility: Visibility.PRIVATE).save()
        ResourceSearchCO resourceSearchCo = new ResourceSearchCO([topicId: 1l])
        session.user = new User()
        Subscription.metaClass.static.countByUserAndTopic={User u,Topic t -> 1 }
        and:
        topic.metaClass.getSubscribedUsers = {[""]}
        topic.metaClass.getTopicPosts = {[""]}
        when:
        controller.show(resourceSearchCo)
        then:
        model.currentUser != null
        model.topic != null
        model.topicPosts.size() != 0
        model.subscribedUsers.size() != 0
    }

    def "check show action with 0 countByUserAndTopic"() {
        given:
        new Topic(createdBy: new User(), name: "t", visibility: Visibility.PRIVATE).save()
        ResourceSearchCO resourceSearchCo = new ResourceSearchCO([topicId: 1l])
        session.user = new User()
        Subscription.metaClass.static.countByUserAndTopic={User u,Topic t -> 0 }
        when:
        controller.show(resourceSearchCo)
        then:
        flash.error == "Without subscription user cannot see private topics"
        response.redirectedUrl == "/login/index"
    }


    def "check save action with save successfully"() {
        given:
        TopicCO topicCO = new TopicCO(topicName:"t",topicUpdatedName: "tt",visibilityString: "PUBLIC")
        User user = new User()
        session.user = user;
        new Topic(createdBy: user, name: "t", visibility: Visibility.PRIVATE).save()

        when:
        controller.save(topicCO)
        def json = JSON.parse(response.text)
        then:
        json["message"] == "Topic saved/updated successfully"

    }
    def "check save action with save unsuccessfully"() {
        given:
        TopicCO topicCO = new TopicCO(topicName:"t",topicUpdatedName: null,visibilityString: null)
        User user = new User()
        session.user = user;
        new Topic().save()

        when:
        controller.save(topicCO)
        def json = JSON.parse(response.text)
        then:
        json["error"] == "Topic not saved/update successfully"
    }

    def "check delete action with error condition"() {
        given:
        new Topic(createdBy: new User(), name: "t", visibility: Visibility.PUBLIC).save()
        when:
        controller.delete(1l)
        then:
        flash.error == "Topic not found"
        response.redirectedUrl == "/login/index"
    }
    def "check delete action with save condition"() {
        given:
        User user = new User()
        session.user = user
        new Topic(createdBy: new User(), name: "t", visibility: Visibility.PUBLIC).save()
        when:
        controller.delete(1l)
        then:
        flash.message == "Successfully topic delete"
        response.redirectedUrl == "/login/index"
    }
    def "check join action without topic exist"() {
        given:
        User user = new User()
        session.user = user
        when:
        controller.join(1l)
        then:
        flash.error == "Topic not exist"
        response.redirectedUrl == "/login/index"
    }
    def "check join action save action"() {
        given:
        User user = new User()
        session.user = user
        new Topic(createdBy: new User(), name: "t", visibility: Visibility.PUBLIC).save()
        when:
        controller.join(1l)
        then:
        flash.message == "Subscription save successfully"
        response.redirectedUrl == "/login/index"
    }

}
