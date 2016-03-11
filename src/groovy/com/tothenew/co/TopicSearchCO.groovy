package com.tothenew.co


import com.tothenew.enums.Visibility
import com.tothenew.User;

class TopicSearchCO extends SearchCO{
    Long id // its user id
    Visibility visibility

    User getUser(){
        return User.get(id)
    }
}

