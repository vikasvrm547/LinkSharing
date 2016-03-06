package com.tothenew

import com.tothenew.co.RegisterCO

class UserController {

    def show() {
        User currentUser = session.user
        render(view: 'index', model: [linkResourceCO: null, tendingTopics: Topic.getTrendingTopics(),
                                      subscribedTopics: currentUser?.getSubscribedTopics(),currentUser:currentUser?:null,
                                      readingItems:currentUser?.getInboxItems()])
    }

  /*  def image(Long userId){
        boolean isUploaded =  User.countByIdAndPhotoIsNotNull(userId)
        if(isUploaded){

        }
    }*/
    def register(RegisterCO registerCO){
        if (registerCO.hasErrors()){
            flash.error = "validations not pass"
            render(view: '/login/index', model:[topPosts: Resource.getTopPosts(), recentShares: Resource.getRecentShares(),
                                                registerCO:registerCO])
        }else {
            User newUser = new User(registerCO.properties)
            newUser.active = true; // Just for checking

            if(!newUser.save()){
                flash.error = "validations not pass"
                render(view: '/login/index', model:[topPosts: Resource.getTopPosts(), recentShares: Resource.getRecentShares(),
                                                    registerCO:newUser])
            }else {
                flash.message = "User successfully created"
                session.user = newUser
                redirect(controller: 'login', action: 'index')
            }
        }

        //render("  ${newUser.firstName}   ${newUser.password}  ${registerCO.properties} <br><br>")
        //render(registerCO.errors.allErrors)
    }
}
