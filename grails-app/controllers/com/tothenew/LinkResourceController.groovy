package com.tothenew

import com.tothenew.co.LinkResourceCO

class LinkResourceController extends ResourceController {

    def save(LinkResourceCO linkResourceCO) {

        if (linkResourceCO?.hasErrors()) {
            flash.error = "Failed to create link resource"
        } else {
            LinkResource linkResource = new LinkResource(url: linkResourceCO.linkResourceLink, description: linkResourceCO.linkResourceComment,
                    topic: Topic.get(linkResourceCO.topicId), createdBy: session.user)
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
