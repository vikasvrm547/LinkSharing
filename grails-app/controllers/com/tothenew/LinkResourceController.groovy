package com.tothenew

import com.tothenew.co.LinkResourceCO
import grails.plugin.springsecurity.annotation.Secured

class LinkResourceController extends ResourceController {
    def springSecurityService

    @Secured(['ROLE_NORMAL'])
    def save(LinkResourceCO linkResourceCO) {
        User currentUser = springSecurityService.getCurrentUser()
        if (linkResourceCO?.hasErrors()) {
            flash.error = "Failed to create link resource"
        } else {
            LinkResource linkResource = new LinkResource(url: linkResourceCO.linkResourceLink,
                    description: linkResourceCO.linkResourceComment, topic: Topic.get(linkResourceCO.topicId),
                    createdBy: currentUser)
            if (linkResource.save()) {
                flash.message = "Link resource successfully save"
                def ctx = startAsync()
                ctx.start {
                    addToReadingItems(linkResource)
                    ctx.complete()
                }
            } else {
                flash.error = "Failed to create link resource"
            }
        }
        redirect(url: request.getHeader("referer"))

    }
}
