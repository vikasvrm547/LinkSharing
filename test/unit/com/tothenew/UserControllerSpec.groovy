package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.co.SearchCO
import com.tothenew.co.TopicSearchCO
import com.tothenew.co.UserCO
import com.tothenew.constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges
import com.tothenew.User;

@Mock([User, EmailService, LinkSharingTagLib, Topic, Resource, ReadingItem, ResourceService])
@TestFor(UserController)
class UserControllerSpec extends Specification {

    @ConfineMetaClassChanges([Topic, User])
    void "check show action"() {
        given:
        User user = new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD, firstName: "vikas", lastName: "verma", active: true).save(flush: true)
        session.user = user
        and:
        Topic.metaClass.static.getTrendingTopics = { [''] }
        and:
        User.metaClass.static.getSubscribedTopics = { [''] }
        and:
        User.metaClass.static.getInboxItems = { SearchCO searchCO -> [""] }
        when:
        controller.show()
        then:
        model.tendingTopics.size != 0
        model.readingItems.size != 0
        model.subscribedTopics.size != 0
        model.searchCO != null
        model.linkResourceCO == null
        model.currentUser != null
    }

    void "check register action with hasErrors"() {
        given:
        UserCO registerCo = new UserCO()
        and:
        Resource.metaClass.static.getTopPosts = { [''] }
        and:
        Resource.metaClass.static.getRecentShares = { [''] }
        and:
        registerCo.metaClass.hasErrors = { true }
        when:
        controller.register(registerCo)
        then:
        model.registerCO != null
        model.recentShares.size != 0
        model.topPosts.size != 0
        flash.error == "com.tothenew.User.controller.register.validation.not.pass"
    }

    void "check checkImageType method"() {
        expect:
        controller.checkImageType(type) == result
        where:
        type         | result
        "image/png"  | true
        'image/jpeg' | true
        'image/gif'  | true
        'image/jpg'  | true
        'pdf'        | false
    }

    void "check with unauthorized email forgotPassword action"() {
        given:
        new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD, confirmPassword: Constants.PASSWORD,
                firstName: "vikas", lastName: "verma", active: true).save(flush: true)
        when:
        controller.forgotPassword("vikasvrm@gmail.com")
        then:
        response.redirectedUrl == '/login/index'
        flash.error == "com.tothenew.User.controller.forgotPassword.authorized.error"
    }

    void "check with inactive user email forgotPassword action"() {
        given:
        new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD, confirmPassword: Constants.PASSWORD,
                firstName: "vikas", lastName: "verma", active: false).save(flush: true)
        when:
        controller.forgotPassword("v1@gmail.com")
        then:
        response.redirectedUrl == '/login/index'
        flash.error == "com.tothenew.User.controller.forgotPassword.authorized.error"
    }

    @ConfineMetaClassChanges(User)
    void "check with failed to update password user email forgotPassword action"() {
        given:
        new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD, confirmPassword: Constants.PASSWORD,
                firstName: "vikas", lastName: "verma", active: true).save(flush: true)
        and:
        User.metaClass.static.updatePassword = { String newPassword, String email ->
            0
        }
        and:
        def mockEmailSevice = Mock(EmailService)
        controller.emailService = mockEmailSevice
        when:
        controller.forgotPassword("v1@gmail.com")
        then:
        response.redirectedUrl == '/login/index'
        flash.error == "com.tothenew.User.controller.forgotPassword.unsuccessfully.update"
    }

    @ConfineMetaClassChanges(User)
    void "check with success to update password user email forgotPassword action"() {
        given:
        new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD, confirmPassword: Constants.PASSWORD,
                firstName: "vikas", lastName: "verma", active: true).save(flush: true)
        and:
        User.metaClass.static.updatePassword = { String newPassword, String email ->
            1
        }
        and:
        def mockEmailSevice = Mock(EmailService)
        controller.emailService = mockEmailSevice
        when:
        controller.forgotPassword("v1@gmail.com")
        then:
        response.redirectedUrl == '/login/index'
        flash.message == "com.tothenew.User.controller.forgotPassword.successfully.update"
    }

    void "check subscribedTopics action"() {
        expect:
        controller.subscribedTopics() != ""
    }

    void "check profile action with no id"() {
        given:
        ResourceSearchCO resourceSearchCO = new ResourceSearchCO(id: 0)
        when:
        controller.profile(resourceSearchCO)
        then:
        response.text == "Sorry"
    }

    void "check profile action with with id"() {
        given:
        ResourceSearchCO resourceSearchCO = new ResourceSearchCO(id: 1)
        session.user = new User()
        and:
        def mockResourceSevice = Mock(ResourceService)
        controller.resourceService = mockResourceSevice
        and:
        mockResourceSevice.search(_ as ResourceSearchCO) >> ["vikas"]
        and:
        User.metaClass.static.get = { Long id -> new User() }
        when:
        controller.profile(resourceSearchCO)
        then:
        model.currentUser != null
        model.user != null
        model.resources.size() != 0
    }

    /*void "check subscriptions action"() {
        given:

        def mockSubscriptionService = Mock(SubscriptionService)
        controller.subscriptionService = mockSubscriptionService
        and:
        mockSubscriptionService.search(_) >> [null]
        when:
        controller.subscriptions(1l)
        then:
        model.topics.size() != 0
    }*/


    void "check toggleActive action with user not found"() {
        when:
        controller.toggleActive(2l)
        then:
        flash.error == "com.tothenew.User.controller.toggle.active.fail"
        response.redirectedUrl == "/user/list"
    }

    void "check toggleActive action with user found but is admin"() {
        given:
        User user = new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD, firstName: "vikas", lastName: "verma", admin: true).save(flush: true)

        when:
        controller.toggleActive(1l)
        then:
        flash.error == "com.tothenew.User.controller.toggle.active.fail"
        response.redirectedUrl == "/user/list"
    }
    void "check toggleActive action with user found and not admin"() {
        given:
        User user = new User(email: "v1@gmail.com", userName: "vikas1", password: Constants.PASSWORD,
                confirmPassword: Constants.PASSWORD, firstName: "vikas", lastName: "verma", admin: false).save(flush: true)

        when:
        controller.toggleActive(1l)
        then:
        flash.message == "com.tothenew.User.controller.toggle.active.success"
        response.redirectedUrl == "/user/list"
    }
}
