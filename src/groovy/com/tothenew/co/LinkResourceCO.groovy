package com.tothenew.co

import grails.validation.Validateable

@Validateable
class LinkResourceCO {
    String linkResourceLink;
    String linkResourceComment;
    Integer topicId;
    static constraints = {
        linkResourceLink(url: true, blank: true)
    }
}
