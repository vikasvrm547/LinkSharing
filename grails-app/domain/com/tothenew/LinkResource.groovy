package com.tothenew

class LinkResource extends Resource {
    String url

    static constraints = {
        url(url: true, blank: true)
    }

    @Override
    Boolean deleteFile() {
        this.delete(flush: true)
        return true
    }

    @Override
    String toString() {
        return url
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        LinkResource that = (LinkResource) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
