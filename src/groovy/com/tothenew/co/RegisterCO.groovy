package com.tothenew.co

import grails.validation.Validateable

@Validateable
class RegisterCO {
    String email
    String userName
    String password
    String firstName
    String lastName
    Byte[] photo
    String confirmPassword;

    static constraints = {
        email( blank: false, email: true)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        userName(blank: false)
        photo(nullable: true)
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, user ->
            return (val != null) && val.equals(user.password)

        })
    }
}
