package com.tothenew.co

import com.tothenew.User;
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

@Validateable
class UpdateUserCO {
    Long userId
    String firstName
    String lastName
    String userName
    MultipartFile userPhoto

    static constraints = {
        importFrom([firstName: 'firstName', lastName: 'lastName'], User)
        userPhoto(nullable: true)
    }

    User getUser() {
        return User.get(userId)
    }
}
