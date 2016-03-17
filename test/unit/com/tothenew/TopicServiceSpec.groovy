package com.tothenew

import com.tothenew.co.TopicSearchCO
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


@Mock([User,Topic,Subscription])
@TestFor(TopicService)
class TopicServiceSpec extends Specification {

    void "check search method"(){
        given:
       /* new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD, firstName: "vikas", lastName: "verma", active: true).save(flush: true)
        Topic topicObj = new Topic(name: "topic1", createdBy: User.get(1), visibility: Visibility.PUBLIC)
        topicObj.save(flush: true)*/
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: 1)
        expect:
        service.search(topicSearchCO).size() == 0
    }
}
