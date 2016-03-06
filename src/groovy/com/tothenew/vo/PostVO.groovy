package com.tothenew.vo

class PostVO {
    Long resourceID
    Long userId
    Long topicId
    Byte resourceRating
    String description
    String url
    String filePath
    byte[] userPhoto
    boolean isRead
    String topicName
    String userUserName
    String userFirstName
    String userLastName

    String getNameOfUser() {
        return "$userFirstName $userLastName"
    }
}
