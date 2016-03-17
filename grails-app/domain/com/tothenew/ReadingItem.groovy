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

    static Boolean getIsRead(Long resourceId, Long userId) {
        createCriteria().get {
            projections {
                property('isRead')
            }
            eq('resource.id', resourceId)
            eq('user.id', userId)
        }
    }


    @Override
    public String toString() {
        return "ReadingItem{" +
                "user=" + user +
                ", isRead=" + isRead +
                '}';
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        ReadingItem that = (ReadingItem) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
