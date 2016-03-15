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

    @Override
    public String toString() {
        return "ResourceRating{" +
                "resource=" + resource +
                ", user=" + user +
                ", score=" + score +
                '}';
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        ResourceRating that = (ResourceRating) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
