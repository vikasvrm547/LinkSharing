package com.tothenew

import com.tothenew.co.TopicSearchCO
import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    List search(TopicSearchCO topicSearchCO  ){
        if(topicSearchCO.id)
        {
            User user = topicSearchCO.getUser()
            return user.getSubscribedTopics()
        }
    }
}
