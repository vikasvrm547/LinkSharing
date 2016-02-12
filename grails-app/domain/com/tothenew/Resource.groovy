package com.tothenew

abstract class Resource {
    String description;
    User createdBy;
    Date dateCreated;
    Date lastUpdated;

    static belongsTo = [topic: Topic];

    static mapping = {
        description(type: 'text')
    }

    static constraints = {
        description(blank: false)
    }

    static hasMany = [readingItems: ReadingItem, ratings: ResourceRating];
}
