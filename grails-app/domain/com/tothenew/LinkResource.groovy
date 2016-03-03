package com.tothenew

class LinkResource extends Resource {
    String url

    static constraints = {
        url(url: true, blank: true)
    }

    @Override
    String toString() {
        return "URL: $url"
    }
}
