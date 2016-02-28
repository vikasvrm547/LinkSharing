package com.tothenew


class LoginController {

    def index() {
        if (session.user) {
            forward(controller: "user", action: "show")
        }else {
            render(view: 'index', model: [topPosts:Resource.getTopPosts(), recentShares:Resource.getRecentShares()])
        }
    }

    def loginHandler(String loginEmailOrUsername, String loginPassword) {
        User user = User.findByUserNameAndPassword(loginEmailOrUsername, loginPassword)
        if(!user){
            user = User.findByEmailAndPassword(loginEmailOrUsername, loginPassword)
        }
        if (user) {
            if (user.active) {
                session.user = user
                redirect(action: 'index')
            }else{
                flash.error =  "user is not active"
                render(flash.error)
            }
        } else {
            flash.error = "User not found"
            redirect(action: 'index')
        }
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }

    def register()
    {
        User newUser = new User('userName': 'normal', email: 'newUser@mail.com', password: "newUserPassword", confirmPassword: "newUserPassword", firstName: 'normal', lastName: 'user', isAdmin: false, isActive: true)

        if(newUser.save(flush: true))
        {
            render "New user added successfully"
        }
        else
        {
            flash.message = "${newUser} could not be added, ${newUser.errors.allErrors}"
            render "${newUser.errors.allErrors.collect { message(error: it) }}"
        }
    }
}
