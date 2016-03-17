package com.tothenew.vo

class PostVO {
    Long resourceID
    Long userId
    Long topicId
    Byte score
    String description
    String url
    String filePath
    Boolean isRead
    String topicName
    String userUserName
    String userFirstName
    String userLastName
    Date lastUpdated;

    String getNameOfUser() {
        return "$userFirstName $userLastName"
    }
}
