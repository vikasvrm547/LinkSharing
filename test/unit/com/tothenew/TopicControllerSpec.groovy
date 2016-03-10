package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock([Subscription, Topic])

@TestFor(TopicController)
class TopicControllerSpec extends Specification {

    @ConfineMetaClassChanges(Subscription)
    void "check show action with error conditions"() {
        given:
        Topic topic = new Topic(name: "topic", createdBy: new User(), visibility: visibility).save(flush: true)
        ResourceSearchCO resourceSearchCo = new ResourceSearchCO([topicId: topicId])

        Subscription.metaClass.static.countByUserAndTopic = { User user, Topic topic1 ->
            flag
        }
        when:
        controller.show(resourceSearchCo)
        then:
        flash.error == error
        response.redirectedUrl == rUrl
        response.text == rText

        where:
        sno | topicId | error                                                 | visibility         | rUrl | rText     | flag
        1   | 111l    | "There is no such topic available"                    | Visibility.PRIVATE | "/"  | ""        | 0
        2   | 1l      | "Without subscription user cannot see private topics" | Visibility.PRIVATE | "/"  | ""        | 0
        3   | 1l      | null                                                  | Visibility.PRIVATE | null | "Success" | 1

    }

    void "check show action with valid conditions"() {

    }

    void "check save action"() {
        given:
        //Topic topic = new Topic(name: "topic", createdBy: new User(), visibility: visibility).save(flush: true)
        String userName = "vikasvrm";
        String password = "vikas12345";
        String firstName = "vikas";
        String lastName = "verma";
        String email = "vikas@gmail.com";
        User userObj = new User(userName: userName, email: email, password: password, firstName: firstName, lastName: lastName);
        session.user = userObj

        when:
        controller.save(topicName, visibility)
        then:
        flash.message == message
        flash.error == error
        response.redirectedUrl == rUrl
        response.text == rText

        where:
        sno | topicName  | visibility | message                      | error                            | rUrl         | rText
        1   | "topic007" | "PUBLIC"   | "Topic created successfully" | null                             | "/user/show" | ""
        2   | "topic008" | "PRIVATE"  | "Topic created successfully" | null                             | "/user/show" | ""
        3   | null       | "PUBLIC"   | null                         | "Topic not created successfully" | null         | "error"
        4   | "topic008" | null       | null                         | "Topic not created successfully" | null         | "error"

    }
}
