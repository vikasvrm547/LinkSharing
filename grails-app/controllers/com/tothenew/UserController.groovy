package com.tothenew

class UserController {

    def show() {
        render(view: 'index', model: [linkResourceCO: null, tendingTopics: Topic?.getTrendingTopics(), subscribedTopics: session.user?.getSubscribedTopics()])
    }

    def index() {
        render(session.user)
    }

}
