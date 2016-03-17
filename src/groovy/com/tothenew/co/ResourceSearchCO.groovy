package com.tothenew.co

import com.tothenew.User
import com.tothenew.enums.Visibility

class ResourceSearchCO extends SearchCO {
    Long topicId
    Long id // its user id
    Visibility visibility

    User getUser() {
        return User.get(id)
    }
}
