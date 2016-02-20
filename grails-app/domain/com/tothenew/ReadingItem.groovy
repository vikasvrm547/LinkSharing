package com.tothenew

class ReadingItem {
    User user
    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [resource: Resource]

    static constraints = {
        user(unique: 'resource')
    }
}
