package com.tothenew

class LoginController {

    def index() {
        if (session.user) {
            forward(controller: "user", action: "index")
        } else {
            render("failed to login")
        }
    }

    def loginHandler(String userName, String password) {
        User user = User.findByUserNameAndPassword(userName, password)
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
            render(flash.error)
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
