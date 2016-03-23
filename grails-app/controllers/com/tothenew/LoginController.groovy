package com.tothenew

import grails.plugin.springsecurity.SpringSecurityUtils

class LoginController {
    def springSecurityService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            forward(controller: "user", action: "show")
        } else {
            render(view: 'index', model:
                    [topPosts: Resource.getTopPosts(), recentShares: Resource.getRecentShares()])
        }
    }

    def loginHandler(String loginEmailOrUsername, String loginPassword) {
        User user = User.findByUsernameAndPassword(loginEmailOrUsername, loginPassword)
        if (!user) {
            user = User.findByEmailAndPassword(loginEmailOrUsername, loginPassword)
        }
        if (user) {
            if (user.active) {
                session.user = user
                //session.uniqueIdForTopicEdit = 1
            } else {
                flash.error = "user is not enabled"
            }
        } else {
            flash.error = "Incorrect credentials"
        }
        redirect(action: 'index')
    }

    def validateEmail() {
        Boolean valid = User.countByEmail(params.email) ? false : true
        render valid
    }

    def validateUserName() {
        Boolean valid = User.countByUsername(params.userName) ? false : true
        render valid
    }

    def logout() {
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
        //session.invalidate()
        //forward(action: 'index')
    }
}
