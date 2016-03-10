package com.tothenew

import com.tothenew.co.RegisterCO
import com.tothenew.co.SearchCO
import grails.converters.JSON

class UserController {
def assetResourceLocator
    def show(SearchCO searchCO) {
        searchCO.max = searchCO.max?:10
        searchCO.offset = searchCO.offset?:0

        User currentUser = session.user

        render(view: 'index', model: [linkResourceCO  : null, tendingTopics: Topic.getTrendingTopics(),
                                      subscribedTopics: currentUser?.getSubscribedTopics(), currentUser: currentUser ?: null,
                                      readingItems    : currentUser?.getInboxItems(searchCO) , totalReadingItems: currentUser.getTotalReadingItem(), searchCO:searchCO])
    }

    def image(Long userId) {
        User user = User.get(userId)
        if (user?.photo) {
            Byte[] img= user.photo
            response.outputStream.write(img)
        } else {
           // File defaultImage = new File("grails-app/assets/images/user/user.png" )
            response.outputStream << assetResourceLocator.findAssetForURI('user.png').getInputStream()
        }
        response.outputStream.flush()
    }

    def register(RegisterCO registerCO) {
        if (registerCO.hasErrors()) {
            flash.error = "validations not pass"

           // println(g.createLink(controller: 'login',action: 'index')) incomplete
            render(view: '/login/index', model: [topPosts  : Resource.getTopPosts(), recentShares: Resource.getRecentShares(),
                                                 registerCO: registerCO])
        } else {
            User newUser = new User(registerCO.properties)
            newUser.active = true; // Just for checking
            newUser.photo = registerCO.userPhoto.bytes

            if (!newUser.save()) {
                flash.error = "validations not pass"
                render(view: '/login/index', model: [topPosts  : Resource.getTopPosts(), recentShares: Resource.getRecentShares(),
                                                     registerCO: newUser])
            } else {
                flash.message = "User successfully created"
                session.user = newUser
                redirect(controller: 'login', action: 'index')
            }
        }
    }
    def subscribedTopics(){
        render(ls.showSubscribedTopics([class: "btn btn-primary form-control create-topic-modal-dropdown"]))
    }
}
