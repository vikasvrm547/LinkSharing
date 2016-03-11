package com.tothenew

import com.tothenew.co.TopicSearchCO
import com.tothenew.vo.TopicVO
import grails.transaction.Transactional

@Transactional
class TopicService {

    List<TopicVO> search(TopicSearchCO topicSearchCO) {
        List<TopicVO> topicVOList = []
        List<Topic> topics = []
        if (topicSearchCO.id) {
            User user = topicSearchCO.getUser()
            if (user) {
                topics = Topic.createCriteria().list() {
                    eq('createdBy.id', topicSearchCO.id)

                    if (topicSearchCO.visibility)
                        eq('visibility', topicSearchCO.visibility)
                }
            }
            topics.each { topic ->
                topicVOList.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility,
                        createdBy: topic.createdBy))
            }
        }

        return topicVOList
    }

}
