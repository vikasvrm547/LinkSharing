package com.tothenew

import grails.transaction.Transactional

@Transactional
class UserService {
    def emailService

    def sendUnreadItemsEmail() {
        getUserWithUnreadItems().each { user ->
            emailService.sendUnreadResourcesEmail(user, getUnreadResources(user))
        }
    }

    List<User> getUserWithUnreadItems() {
        return Resource.usersWithUnreadResources()
    }

    List<Resource> getUnreadResources(User user) {
        return user.unreadResources()
    }
}
