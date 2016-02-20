package com.tothenew

class ResourceRating {
    User user
    int score
    Date dateCreated
    Date lastUpdated

    static belongsTo = [resource: Resource]

    static constraints = {
        score(min: 1, max: 5)
        user(unique: 'resource')
    }
}
