package com.tothenew

import com.tothenew.constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.web.api.ControllersApi
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock([Resource, ResourceRating, User])
class LoginControllerSpec extends Specification {


    void "check index method with session"() {
        given:
        User user = new User(email: "v1@gmail.com",
                userName: "vikas1",
                password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                active: true)
        and:
        session.user = user
        when:
        controller.index()
        then:
        response.forwardedUrl == '/user/show'
    }

    void "check index method without session"() {
        given:
        User user = new User(email: "v1@gmail.com",
                userName: "vikas1",
                password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                active: true)

        when:
        controller.index()
        then:
        response.text == "failure"
    }

    void "check logout method"() {
        given:
        User user = new User(email: "v1@gmail.com",
                userName: "vikas1",
                password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                active: true)
        and:
        session.user = user
        when:
        controller.logout()
        then:
        session.user == null
        response.forwardedUrl == '/login/index'
    }

    void "check loginhandler method with error conditions"() {
        given:
        User user = new User(email: "v1@gmail.com",
                userName: "vikas1",
                password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                active: active).save(flush: true)


        when:
        controller.loginHandler(loginEmailOrUsername, password)
        then:
        flash.error == error
        response.redirectedUrl == rUrl
        response.text == ren
        where:

        sno | loginEmailOrUsername | password           | active | rUrl | error                | ren
        1   | "qwe"                | "ASD"              | false  | "/"  | "User not found"     | ""
        2   | "vikas1"             | Constants.PASSWORD | false  | null | "user is not active" | "user is not active"
        3   | "vikas1"             | Constants.PASSWORD | true   | "/"  | ""                   | ""
    }

    void "check loginhandler method with valid conditions"() {
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
        controller.loginHandler("vikas1", Constants.PASSWORD)
        then:

        response.redirectedUrl == "/"
        session.user.toString().equals("vikas1")
    }

}
