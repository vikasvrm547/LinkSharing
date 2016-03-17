package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.co.TopicSearchCO
import com.tothenew.co.UpdatePasswordCO
import com.tothenew.co.UpdateUserCO
import com.tothenew.co.UserCO
import com.tothenew.co.SearchCO
import com.tothenew.co.UserSearchCO
import com.tothenew.dto.EmailDTO
import com.tothenew.enums.Visibility
import com.tothenew.util.Utility
import com.tothenew.vo.UserVO

class UserController {
    def assetResourceLocator
    def subscriptionService
    def topicService
    def resourceService
    def emailService

    def show(SearchCO searchCO) {
        searchCO.max = searchCO.max ?: 9
        searchCO.offset = searchCO.offset ?: 0

        User currentUser = session.user

        render(view: 'index', model: [linkResourceCO   : null, tendingTopics: Topic?.getTrendingTopics(),
                                      subscribedTopics : currentUser?.getSubscribedTopics(),
                                      currentUser      : currentUser ?: null,
                                      readingItems     : currentUser?.getInboxItems(searchCO),
                                      totalReadingItems: currentUser?.getTotalReadingItem(),
                                      searchCO         : searchCO])
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
            flash.error = g.message(code: "com.tothenew.User.controller.register.validation.not.pass")
            render(view: '/login/index', model: [topPosts  : Resource.getTopPosts(), recentShares: Resource.getRecentShares(),
                                                 registerCO: registerCO])
        } else if ((!registerCO.userPhoto?.bytes) || checkImageType(registerCO.userPhoto?.contentType)) {
            User newUser = new User(registerCO.properties)
            newUser.photo = registerCO.userPhoto.bytes
            if (!newUser.save()) {
                flash.error = g.message(code: "com.tothenew.User.controller.register.validation.not.pass")
                render(view: '/login/index', model: [topPosts  : Resource.getTopPosts(), recentShares: Resource.getRecentShares(),
                                                     registerCO: newUser])
            } else {
                flash.message = g.message(code: "com.tothenew.User.controller.register.successfully.created")
                redirect(controller: 'login', action: 'index')
            }
        } else {
            registerCO.errors.rejectValue('userPhoto', 'nullable')
            render(view: '/login/index', model: [topPosts  : Resource.getTopPosts(), recentShares: Resource.getRecentShares(),
                                                 registerCO: registerCO])
        }

    }

    def subscribedTopics() {
        render(ls.showSubscribedTopics([class: "btn btn-primary form-control create-topic-modal-dropdown"]))
    }

    def profile(ResourceSearchCO resourceSearchCO) {
        if (resourceSearchCO.id) {
            User user = User.get(resourceSearchCO.id)
            User currentUser = session.user
            render(view: 'profile', model: [currentUser: currentUser, user: user,
                                            resources  : resourceService.search(resourceSearchCO)])
        } else render("Sorry")
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
                topicSearchCO.visibility = null
                // topicSearchCO.visibility = Visibility.PUBLIC
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
            user.confirmPassword = user.password
            if (user.save(flush: true)) {
                flash.message = g.message(code: "com.tothenew.User.controller.toggle.active.success")
            } else {
                flash.error = g.message(code: "com.tothenew.User.controller.toggle.active.fail")
            }
        } else {
            flash.error = g.message(code: "com.tothenew.User.controller.toggle.active.fail")
        }
        redirect(controller: 'user', action: "list")

    }

    def list(UserSearchCO userSearchCO) {
        List<UserVO> userVOList = []
        userSearchCO.max = userSearchCO.max ?: 20
        userSearchCO.offset = userSearchCO.offset ?: 0
        if (session.user?.admin) {
            List users = User.search(userSearchCO).list([sort  : userSearchCO.sort, order: userSearchCO.order, max: userSearchCO.max,
                                                         offset: userSearchCO.offset])
            users.each { user ->
                userVOList.add(new UserVO(id: user.id, userName: user.userName, email: user.email,
                        firstName: user.firstName,
                        lastName: user.lastName, active: user.active))
            }
            render(view: 'list', model: [users: userVOList, userSearchCO: userSearchCO, totalCount: users.totalCount])
        } else {
            redirect(controller: 'login', action: 'index')
        }
    }

    def forgotPassword(String email) {
        User user = User.findByEmail(email)
        if (user && user.active) {
            String newPassword = Utility.getRandomPassword()
            user.password = newPassword
            EmailDTO emailDTO = new EmailDTO(to: [email], subject: "Your Link sharing New Password",
                    view: '/email/_password', model: [newPassword: newPassword])
            emailService.sendMail(emailDTO)
            if (User.updatePassword(newPassword, email)) {
                flash.message = g.message(code: "com.tothenew.User.controller.forgotPassword.successfully.update")
            } else {
                flash.error = g.message(code: "com.tothenew.User.controller.forgotPassword.unsuccessfully.update")
            }
        } else {
            flash.error = g.message(code: "com.tothenew.User.controller.forgotPassword.authorized.error")
        }
        redirect(controller: "login", action: "index")
    }

    def edit() {
        [currentUser: session.user]
    }

    def save(UpdateUserCO updateUserCO) {
        User user = updateUserCO.getUser()
        if (user) {
            user.firstName = updateUserCO.firstName
            user.lastName = updateUserCO.lastName
            user.userName = updateUserCO.userName
            if (updateUserCO.userPhoto.bytes) {
                if (checkImageType(updateUserCO.userPhoto.contentType))
                    user.photo = updateUserCO.userPhoto.bytes
                else
                    user.errors.rejectValue('photo', 'nullable')
            }
            user.confirmPassword = user.password
            if ((!user.hasErrors()) && user.save(flush: true)) {
                session.user = user
                flash.message = "successfully update your details"
            } else flash.error = "Failed to update your details"
        } else flash.error = "Something went wrong"
        render(view: "/user/edit", model: [currentUser: session.user, user: user])
    }

    def updatePassword(UpdatePasswordCO updatePasswordCO) {
        User user = updatePasswordCO.getUser()
        if (user) {
            if (user.password.equals(updatePasswordCO.oldPassword)) {
                user.password = updatePasswordCO.password
                user.confirmPassword = updatePasswordCO.password
                if (user.save(flush: true)) {
                    session.user = user
                    flash.message = "Successfully update password"
                } else {
                    flash.error = "Could not update password"
                }
            } else {
                flash.error = "Old password is not correct"
            }
        } else {
            flash.error = "User not exist"
        }
        render(view: "/user/edit", model: [currentUser: session.user, user: user])
    }

    Boolean checkImageType(String type) {
        def imageContentTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif']
        if (imageContentTypes.contains(type)) return true
        else return false
    }
}
