package com.tothenew

import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([User, Topic, Subscription])
class SubscriptionControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "check delete action"() {
        given:
        Subscription subscription = new Subscription(user: new User(), topic: new Topic())
        subscription.save(flush: true)
        when:
        controller.delete(subscriptionId)
        then:
        response.text == rText
        where:
        sno | subscriptionId | rText
        1   | 1l             | "Subscription successfully deleted"
        2   | 2l             | "Subscription not found"
    }

    def "check update action"() {
        given:
        Subscription subscription = new Subscription(user: new User(), topic: new Topic())
        subscription.save(flush: true)
        when:
        controller.update(seriousnessId, seriousness)
        then:
        response.text == rText
        where:
        sno | seriousnessId | seriousness | rText
        1   | 1l            | "asd"       | "Seriousness not valid"
        2   | 1l            | null        | "Seriousness not valid"
        3   | 11111l        | "serious"   | "Subscription not found"
        4   | 1l            | "casual"    | "Subscription updated successfully"
    }

    def "check save action"() {
        given:
        session.user = user
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PUBLIC, createdBy: new User())
        topic.save(flush: true)

        when:
        controller.save(topicId)
        then:
        response.text == rText
        flash.error == fError
        where:
        sno | topicId | user       | fError                                 | rText
        1   | 144444l | null       | "topic not found to save subscription" | "topic not found to save subscription"
        2   | 1l      | new User() | "Subscription not save successfully"   | "Subscription not save successfully"
        3   | 1l      | new User() | "Subscription save successfully"       | "Subscription save successfully"


    }
}
