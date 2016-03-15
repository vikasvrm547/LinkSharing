package com.tothenew

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ResourceController)
@Mock([Resource,User,LinkResource,DocumentResource,Topic])
class ResourceControllerSpec extends Specification {


    void "check delete action of link resource"() {
        given:
        LinkResource linkResource = new LinkResource(description: "s1",createdBy: new User(),
                url: 'http://www.tothenew.com/',topic: new Topic())

        session.user = new User(id: 1l)
        session.user.metaClass.canDeleteResource = {Long id-> true}
        linkResource.save(flush: true)

        when:
        controller.delete(resourceId)
        then:
        response.redirectedUrl == rUrl
        where:
        sno | resourceId | rUrl
        1   | 1l         | "/login/index"
        2   | 2l         | "/login/index"
    }
}
