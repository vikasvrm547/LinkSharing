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
    static Boolean getIsRead(Long resourceId, Long userId){
        createCriteria().get{
            projections{
                property('isRead')
            }
            eq('resource.id',resourceId)
            eq('user.id',userId)
        }

    }
}
