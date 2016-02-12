package com.tothenew

class User {
    String email;
    String userName;
    String password;
    String firstName;
    String lastName;
    Byte[] photo;
    Boolean admin = false;
    Boolean active = false;
    Date dateCreated;
    Date lastUpdated;

    static transients = ['name']

    static mapping = {
        photo(sqlType: 'longblob')
    }
    
    static constraints = {
        email(unique: true, blank: false, email: true)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        userName(unique: true, blank: false)
        photo(nullable: true)
        active(nullable: true)
        admin(nullable: true)
    }

    String getName() {
        [firstName, lastName].findAll { it }.join(" ")
    }

    static hasMany = [topics   : Topic, subscriptions: Subscription, resourceItems: ReadingItem,
                      resources: Resource, resourceRatings: ResourceRating];

}
