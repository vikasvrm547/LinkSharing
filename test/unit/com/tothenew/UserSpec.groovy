package com.tothenew

import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


@TestFor(User)
@Mock([Topic, User,Subscription])
class UserSpec extends Specification {

    @Unroll("check user constraints sno---------#sno")
    def "check user constraints excluding email uniqueness verification"() {
        given:
        User userObj = new User(userName: userName, email: email, password: password, confirmPassword: confirmPassword, firstName: firstName, lastName: lastName, photo: photo, admin: isAdmin, active: isActive)

        when:
        boolean result = userObj.validate()
        
        then:
        result == expected

        where:
        sno | email          | userName   | password    | confirmPassword | firstName | lastName | photo | isAdmin | isActive | expected
        1   | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | true
        2   | "vikas"        | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        3   | " "            | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        4   | null           | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        5   | "vikas@as.com" | " "        | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        6   | "vikas@as.com" | null       | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        7   | "vikas@as.com" | "vikasvrm" | "1234"      | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        8   | "vikas@as.com" | "vikasvrm" | " "         | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        9   | "vikas@as.com" | "vikasvrm" | null        | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | true     | false
        10  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | " "       | "verma"  | 12    | true    | true     | false
        11  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | null      | "verma"  | 12    | true    | true     | false
        12  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | " "      | 12    | true    | true     | false
        13  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | null     | 12    | true    | true     | false
        14  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | null  | true    | true     | true
        15  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | null    | true     | true
        16  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas1234"     | "vikas"   | "verma"  | 12    | true    | null     | true
        17  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "asdasd"        | "vikas"   | "verma"  | 12    | true    | null     | false


    }

    def "check email uniqueness"() {
        given:

        String userName = "vikasvrm"
        String password = "vikas12345"
        String confirmPassword = "vikas12345"
        String firstName = "vikas"
        String lastName = "verma"
        String email = "vikas@gmail.com"
        User userObj = new User(userName: userName, email: email, password: password,confirmPassword:confirmPassword, firstName: firstName, lastName: lastName)

        when:
        userObj.save()

        then:
        User.count() == 1

        when:
        userObj = new User(userName: "jim", email: email, password: password,confirmPassword:confirmPassword, firstName: "jim", lastName: "john")
        userObj.save()

        then:
        User.count() == 1
        userObj.errors.allErrors.size() == 1
        userObj.errors.getFieldErrorCount('email') == 1
    }

    def "check userName uniqueness"() {
        given:

        String userName = "vikasvrm"
        String password = "vikas12345"
        String firstName = "vikas"
        String lastName = "verma"
        String email = "vikas@gmail.com"
        String confirmPassword = "vikas12345"

        User userObj = new User(userName: userName, email: email, password: password,confirmPassword: confirmPassword, firstName: firstName, lastName: lastName)

        when:
        userObj.save()

        then:
        User.count() == 1

        when:
        userObj = new User(userName: userName, email: "vfsf@as.com", password: password,confirmPassword: confirmPassword, firstName: firstName, lastName: lastName)
        userObj.save()

        then:
        User.count() == 1
        userObj.errors.allErrors.size() == 1
        userObj.errors.getFieldErrorCount('userName') == 1
    }

    @Unroll("check getName method sno---------#sno")
    def "check getName method"() {
        given:
        String userName = "vikasvrm1"
        String password = "vikas12345"
        String confirmPassword = "vikas12345"

        String email = "vikas1@gmail.com"
        User userObj = new User(userName: userName, email: email, password: password,confirmPassword:confirmPassword, firstName: firstName, lastName: lastName)

        when:
        String result = userObj.getName()

        then:
        result == expected

        where:
        sno | firstName | lastName | expected
        1   | "vikas"   | "verma"  | "vikas verma"
        2   | ""        | ""       | ""
        3   | null      | "verma"  | "verma"
        4   | null      | null     | ""
    }

    def "Check toString of user"() {
        setup:
        User user = new User(userName: userName)

        when:
        String name = user.toString()

        then:
        name == result

        where:
        userName       | result
        "testUserName" | "testUserName"
    }

    def "check isSubscribed method with valid result"() {
        given:
        String userName = "vikasvrm"
        String password = "vikas12345"
        String confirmPassword = "vikas12345"
        String firstName = "vikas"
        String lastName = "verma"
        String email = "vikas@gmail.com"
        User user = new User(userName: userName, email: email, password: password,confirmPassword:confirmPassword, firstName: firstName, lastName: lastName)
        user.save(flush: true)
        Topic topic = new Topic(name: "topic1", createdBy: user, visibility: Visibility.PUBLIC)
        topic.save(flush: true)

        expect:
        User.get(1).isSubscribed(topic) == true
    }

    def "check isSubscribed method with invalid result"() {
        given:
        String userName = "vikasvrm"
        String password = "vikas12345"
        String confirmPassword = "vikas12345"
        String firstName = "vikas"
        String lastName = "verma"
        String email = "vikas@gmail.com"
        User user1 = new User(userName: userName, email: email, password: password,confirmPassword:confirmPassword, firstName: firstName, lastName: lastName)

        User user2 = new User(userName: "asd", email: "asd@asd.com", password: password,confirmPassword:confirmPassword, firstName: firstName, lastName: lastName)

        user1.save(flush: true)
        user2.save(flush: true)

        Topic topic = new Topic(name: "topic1", createdBy: User.get(1), visibility: Visibility.PUBLIC)
        topic.save(flush: true)

        expect:
        User.get(2).isSubscribed(topic) == false
    }
}

