package com.tothenew.co

import com.tothenew.User;


class TopicCO {
    Long userId
    Long topicId
    String topicName
    String topicUpdatedName
    String visibilityString

    User getUser() {
        return User.get(userId)
    }
}
