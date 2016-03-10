package com.tothenew

import grails.converters.JSON
import org.apache.xpath.operations.Bool


class LoginController {

    def index() {
        if (session.user) {
            forward(controller: "user", action: "show")
        } else {
            render(view: 'index', model:
                    [topPosts: Resource.getTopPosts(), recentShares: Resource.getRecentShares()])
        }
    }

    def loginHandler(String loginEmailOrUsername, String loginPassword) {
        User user = User.findByUserNameAndPassword(loginEmailOrUsername, loginPassword)
        if (!user) {
            user = User.findByEmailAndPassword(loginEmailOrUsername, loginPassword)
        }
        if (user) {
            if (user.active) {
                session.user = user
                redirect(action: 'index')
            } else {
                flash.error = "user is not active"
                render(flash.error)
            }
        } else {
            flash.error = "User not found"
            redirect(action: 'index')
        }
    }

    def validateEmail() {
        Boolean valid = User.countByEmail(params.email) ? false : true
        render valid
    }
    def validateUserName() {
        Boolean valid = User.countByUserName(params.userName) ? false : true
        render valid
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }


}
