package com.tothenew.vo

import com.tothenew.User;
import com.tothenew.enums.Visibility

class TopicVO {
    Long id
    String name
    Visibility visibility
    Long count
    User createdBy

    String toString() {
        return "Name: $name"
    }
}
