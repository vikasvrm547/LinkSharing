package com.tothenew

import com.tothenew.enums.Seriousness

class Subscription {
    User user
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness = Seriousness.SERIOUS

    static belongsTo = [topic: Topic]

    static constraints = {
        user(unique: 'topic')
    }
    static mapping = {
        user lazy: false
        topic lazy: false
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Subscription that = (Subscription) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "topic=" + topic +
                ", user=" + user +
                ", seriousness=" + seriousness +
                '}';
    }
}
