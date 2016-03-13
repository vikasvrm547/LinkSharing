package com.tothenew

import com.tothenew.constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock([User])
@TestFor(UserController)
class UserControllerSpec extends Specification {


    void "check show action"() {
        given:
        User user = new User(email: "v1@gmail.com",
                userName: "vikas1",
                password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                active: true).save(flush: true)
        session.user = user
        when:
        controller.index()
        then:
        response.text =="vikas1"


    }
}
