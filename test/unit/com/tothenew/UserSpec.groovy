package com.tothenew

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


@TestFor(User)
class UserSpec extends Specification {

    @Unroll("check user constraints sno---------#sno")
    def "check user constraints excluding email uniqueness verification"() {
        given:
        User userObj = new User(userName: userName, email: email, password: password, firstName: firstName, lastName: lastName, photo: photo, admin: isAdmin, active: isActive);

        when:
        boolean result = userObj.validate();

        then:
        result == expected

        where:
        sno | email          | userName   | password    | firstName | lastName | photo | isAdmin | isActive | expected
        1   | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas"   | "verma"  | 12    | true    | true     | true
        2   | "vikas"        | "vikasvrm" | "vikas1234" | "vikas"   | "verma"  | 12    | true    | true     | false
        3   | " "            | "vikasvrm" | "vikas1234" | "vikas"   | "verma"  | 12    | true    | true     | false
        4   | null           | "vikasvrm" | "vikas1234" | "vikas"   | "verma"  | 12    | true    | true     | false
        5   | "vikas@as.com" | " "        | "vikas1234" | "vikas"   | "verma"  | 12    | true    | true     | false
        6   | "vikas@as.com" | null       | "vikas1234" | "vikas"   | "verma"  | 12    | true    | true     | false
        7   | "vikas@as.com" | "vikasvrm" | "1234"      | "vikas"   | "verma"  | 12    | true    | true     | false
        8   | "vikas@as.com" | "vikasvrm" | " "         | "vikas"   | "verma"  | 12    | true    | true     | false
        9   | "vikas@as.com" | "vikasvrm" | null        | "vikas"   | "verma"  | 12    | true    | true     | false
        10  | "vikas@as.com" | "vikasvrm" | "vikas1234" | " "       | "verma"  | 12    | true    | true     | false
        11  | "vikas@as.com" | "vikasvrm" | "vikas1234" | null      | "verma"  | 12    | true    | true     | false
        12  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas"   | " "      | 12    | true    | true     | false
        13  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas"   | null     | 12    | true    | true     | false
        14  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas"   | "verma"  | null  | true    | true     | true
        15  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas"   | "verma"  | 12    | null    | true     | true
        16  | "vikas@as.com" | "vikasvrm" | "vikas1234" | "vikas"   | "verma"  | 12    | true    | null     | true


    }

    def "check email uniqueness"() {
        given:

        String userName = "vikasvrm";
        String password = "vikas12345";
        String firstName = "vikas";
        String lastName = "verma";
        String email = "vikas@gmail.com";
        User userObj = new User(userName: userName, email: email, password: password, firstName: firstName, lastName: lastName);

        when:
        userObj.save();

        then:
        User.count() == 1;

        when:
        userObj = new User(userName: "jim", email: email, password: password, firstName: "jim", lastName: "john");
        userObj.save();

        then:
        User.count() == 1;
        userObj.errors.allErrors.size() == 1;
        userObj.errors.getFieldErrorCount('email') == 1;
    }

    def "check userName uniqueness"() {
        given:

        String userName = "vikasvrm";
        String password = "vikas12345";
        String firstName = "vikas";
        String lastName = "verma";
        String email = "vikas@gmail.com";
        User userObj = new User(userName: userName, email: email, password: password, firstName: firstName, lastName: lastName);

        when:
        userObj.save();

        then:
        User.count() == 1;

        when:
        userObj = new User(userName: userName, email: "vfsf@as.com", password: password, firstName: firstName, lastName: lastName);
        userObj.save();

        then:
        User.count() == 1;
        userObj.errors.allErrors.size() == 1;
        userObj.errors.getFieldErrorCount('userName') == 1;
    }


    def "check getName method"() {
        given:
        String userName = "vikasvrm1";
        String password = "vikas12345";
        String email = "vikas1@gmail.com";
        User userObj = new User(userName: userName, email: email, password: password, firstName: firstName, lastName: lastName);

        when:
        String result = userObj.getName();

        then:
        result == expected

        where:
        firstName | lastName | expected
        "vikas"   | "verma"  | "vikas verma"
        " "       | " "      | "  "
        null      | "verma"  | "null verma"
        null      | null     | ""
    }
}

