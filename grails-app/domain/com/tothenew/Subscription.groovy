package com.tothenew

import enums.Seriousness

class Subscription {
    User user;
    Date dateCreated;
    Date lastUpdated;
    Seriousness seriousness;

    static belongsTo = [topic: Topic];

    static constraints = {
        user(unique: 'topic')
    }
}
