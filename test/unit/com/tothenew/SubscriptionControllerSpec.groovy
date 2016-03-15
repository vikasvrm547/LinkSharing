package com.tothenew

import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility
import grails.converters.JSON
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([User, Topic, Subscription])
class SubscriptionControllerSpec extends Specification {


    void "check delete action"() {
        given:
        Subscription subscription = new Subscription(user: new User(), topic: new Topic())
        subscription.save(flush: true)
        Topic.metaClass.static.get ={ Long id -> new Topic() }
        when:
        controller.delete(subscriptionId)
        then:
        def json = JSON.parse(response.text)
        json['error'] == rText
        where:
        sno | subscriptionId | rText
        1   | 1l             | "Creator of topic cannot delete subscription"
        2   | 2l             | "Creator of topic cannot delete subscription"
    }

    def "check update action"() {
        given:
        Subscription subscription = new Subscription(user: new User(), topic: new Topic())
        subscription.save(flush: true)
        Subscription.metaClass.static.createCriteria = { new Subscription()}
        when:
        controller.update(seriousnessId, seriousness)
        then:
        def json = JSON.parse(response.text)
        json['error'] == rText
        where:
        sno | seriousnessId | seriousness | rText
        1   | 1l            | "asd"       | "Seriousness not valid"
        3   | 11111l        | "serious"   | "Subscription not found"
        4   | 1l            | "casual"    | "Subscription not found"
    }

    def "check save action"() {
        given:
        session.user = user
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PUBLIC, createdBy: new User())
        topic.save(flush: true)

        when:
        controller.save(topicId)
        then:
        def json = JSON.parse(response.text)
        json['error'] == rText
        where:
        sno | topicId | user       | rText
        1   | 144444l | null       | "topic not found to save subscription"
        2   | 1l      | new User() | "Subscription not save successfully"
        3   | 1l      | new User() | "Subscription not save successfully"

    }
}
