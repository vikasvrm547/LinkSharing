package com.tothenew

import enums.Visibility

class Topic {
    String name;
    User createdBy;
    Date dateCreated;
    Date lastUpdated;
    Visibility visibility;

    static constraints = {
        name(blank: false, unique: 'createdBy')
        visibility(inList: Visibility.values() as List)
    }

    static hasMany = [subscriptions: Subscription, resources: Resource];
}
