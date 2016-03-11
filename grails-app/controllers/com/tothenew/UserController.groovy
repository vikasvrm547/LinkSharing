package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.co.TopicSearchCO
import com.tothenew.co.UserCO
import com.tothenew.co.SearchCO
import com.tothenew.co.UserSearchCO
import com.tothenew.enums.Visibility
import com.tothenew.vo.TopicVO
import com.tothenew.vo.UserVO

class UserController {
    def assetResourceLocator
    def subscriptionService
    def topicService
    def resourceService

    def show(SearchCO searchCO) {
        searchCO.max = searchCO.max ?: 10
        searchCO.offset = searchCO.offset ?: 0

        User currentUser = session.user

        render(view: 'index', model: [linkResourceCO  : null, tendingTopics: Topic.getTrendingTopics(),
                                      subscribedTopics: currentUser?.getSubscribedTopics(), currentUser: currentUser ?: null,
                                      readingItems    : currentUser?.getInboxItems(searchCO), totalReadingItems: currentUser.getTotalReadingItem(),
                                      searchCO        : searchCO])
    }

    def image(Long userId) {
        User user = User.get(userId)
        if (user?.photo) {
            Byte[] img = user.photo
            response.outputStream.write(img)
        } else {
            // File defaultImage = new File("grails-app/assets/images/user/user.png" )
            response.outputStream << assetResourceLocator.findAssetForURI('user.png').getInputStream()
        }
        response.outputStream.flush()
    }

    def register(UserCO registerCO) {
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

    def subscribedTopics() {
        render(ls.showSubscribedTopics([class: "btn btn-primary form-control create-topic-modal-dropdown"]))
    }

    def profile(ResourceSearchCO resourceSearchCO) {
        //  ResourceSearchCO resourceSearchCO = new ResourceSearchCO(id: 1l)
        if (resourceSearchCO.id) {
            User user = User.get(resourceSearchCO.id)
            User currentUser = session.user
            render(view: 'profile', model: [currentUser: currentUser, user: user,
                                            resources  : resourceService.search(resourceSearchCO)])
        } else render("nooooooooo")
    }

    def subscriptions(Long id) {
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)
        User currentUser = session.user
        if (currentUser) {
            if (!(currentUser.admin || currentUser.id == id)) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List subscribedTopics = subscriptionService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: subscribedTopics])

    }


    def topics(Long id) {
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)
        User currentUser = session.user
        if (currentUser) {
            if (!(currentUser.admin || currentUser.id == id)) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List createdTopics = topicService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: createdTopics])
    }

    def toggleActive(Long id) {
        User user = User.get(id)

        if (user && (!user.admin)) {
            user.active = !user.active
            if (user.save(flush: true, validate: false)) {
                flash.message = "Toggle successfully"
            } else {
                flash.error = "Can't toggle successfully ${user.errors.allErrors}"
            }
        } else {
            flash.error = "Can't toggle successfully"
        }
        redirect(controller: 'user', action: "list")

    }

    def list(UserSearchCO userSearchCO) {
        List<UserVO> userVOList = []
        if (session.user?.admin) {
            User.search(userSearchCO).list([sort:userSearchCO.sort,order:userSearchCO.order]).each { user ->
                userVOList.add(new UserVO(id: user.id, userName: user.userName, email: user.email, firstName: user.firstName,
                        lastName: user.lastName, active: user.active))
            }
            render(view: 'list', model: [users: userVOList])
        } else {
            redirect(controller: 'login', action: 'index')
        }
    }
}
