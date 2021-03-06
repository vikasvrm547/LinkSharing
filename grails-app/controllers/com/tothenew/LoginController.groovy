package com.tothenew

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
                session.uniqueIdForTopicEdit = 1
            } else {
                flash.error = "user is not active"
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
        Boolean valid = User.countByUserName(params.userName) ? false : true
        render valid
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }
}
