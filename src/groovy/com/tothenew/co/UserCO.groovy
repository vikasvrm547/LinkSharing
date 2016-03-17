package com.tothenew.co

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

@Validateable
class UserCO {
    String email
    String userName
    String password
    String firstName
    String lastName
    MultipartFile userPhoto
    String confirmPassword;

    static constraints = {
        email(blank: false, email: true)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        userName(blank: false)
        userPhoto(nullable: true)
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, user ->
            return (val != null) && val.equals(user.password) ?: "com.tothenew.co.RegisterCO.confirmPassword.validator"
        })
    }

}
