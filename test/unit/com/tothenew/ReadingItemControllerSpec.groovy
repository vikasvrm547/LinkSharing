package com.tothenew

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([ReadingItem])
@TestFor(ReadingItemController)
class ReadingItemControllerSpec extends Specification {
    void "check changeIs Read action with unsuccessfull update"() {
        given:
        session.user = new User(id: 1)
        ReadingItem.metaClass.static.executeUpdate = { String query, Map m -> 0 }
        when:
        controller.changeIsRead(1l, true)
        then:
        flash.error == "Reading item could not update successfully"
        response.redirectedUrl != null
    }

    void "check changeIs Read action with successfull update"() {
        given:
        session.user = new User(id: 1)
        ReadingItem.metaClass.static.executeUpdate = { String query, Map m -> 1 }
        when:
        controller.changeIsRead(1l, true)
        then:
        flash.message == "Reading item successfully updated"
        response.redirectedUrl != null
    }
}
