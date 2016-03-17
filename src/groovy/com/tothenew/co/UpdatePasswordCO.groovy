package com.tothenew.co

import com.tothenew.User
import grails.validation.Validateable;

@Validateable
class UpdatePasswordCO {
    Long userId
    String oldPassword
    String password

    static constraints = {
        password(blank: false, minSize: 5)
    }

    User getUser() {
        return User.get(userId)
    }
}
