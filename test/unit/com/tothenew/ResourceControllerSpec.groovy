package com.tothenew

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ResourceController)
@Mock([Resource,User,LinkResource,DocumentResource,Topic])
class ResourceControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "check delete action of link resource"() {
        given:
        LinkResource linkResource = new LinkResource(description: "s1",createdBy: new User(),
                url: 'http://www.tothenew.com/',topic: new Topic())
        linkResource.save(flush: true)
        when:
        controller.delete(resourceId)
        then:
        response.text == rText
        where:
        sno | resourceId | rText
        1   | 1l         | "Resource successfully deleted"
        2   | 2l         | "Resource not found"
    }
}
