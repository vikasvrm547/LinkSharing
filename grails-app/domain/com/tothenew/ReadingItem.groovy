package com.tothenew

class ReadingItem {
    Resource resource;
    User user;
    Boolean isRead;
    Date dateCreated;
    Date lastUpdated;

    static belongsTo = [resource: Resource]

    static constraints = {
        user(unique: 'resource')
    }
}
