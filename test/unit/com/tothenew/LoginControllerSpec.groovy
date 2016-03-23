package com.tothenew

import com.tothenew.constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(LoginController)
@Mock([Resource, ResourceRating, User])
class LoginControllerSpec extends Specification {


    void "check index method with session"() {
        given:
        User user = new User(email: "v1@gmail.com", username: "vikas1", password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD, firstName: "vikas", lastName: "verma", active: true)
        and:
        session.user = user
        when:
        controller.index()
        then:
        response.forwardedUrl == '/user/show'
    }

    void "check index method without session"() {
        given:
        when:
        controller.index()
        then:
        response.text == "failure"
    }

    void "check logout method"() {
        given:
        User user = new User(email: "v1@gmail.com", username: "vikas1", password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD, firstName: "vikas", lastName: "verma", active: true)
        and:
        session.user = user
        when:
        controller.logout()
        then:
        session.user == null
        response.forwardedUrl == '/login/index'
    }

    @Unroll("#sno---------")
    void "check loginhandler method with error conditions"() {
        given:

        when:
        controller.loginHandler(loginEmailOrUsername, password)
        then:
        flash.error == error
        response.redirectedUrl == rUrl
        response.text == ren
        where:

        sno | loginEmailOrUsername | password           | active | rUrl           | error                   | ren
        1   | "qwe"                | "ASD"              | false  | "/login/index" | "Incorrect credentials" | ""
        2   | "vikas1"             | Constants.PASSWORD | false  | "/login/index" | "Incorrect credentials" | ""
        3   | "vikas1"             | Constants.PASSWORD | true   | "/login/index" | "Incorrect credentials" | ""
    }

    void "check loginhandler method with valid conditions"() {
        given:
        User user = new User(email: "v1@gmail.com",
                username: "vikas1",
                password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                active: true).save(flush: true)
        session.user = user

        when:
        controller.loginHandler("vikas1", Constants.PASSWORD)
        then:

        response.redirectedUrl == "/login/index"
        session.user.toString().equals("vikas1")
    }

}
