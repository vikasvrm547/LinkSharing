package com.tothenew

import com.tothenew.co.LinkResourceCO

class LinkResourceController extends ResourceController{

    def save(LinkResourceCO linkResourceCO) {

        if (linkResourceCO?.hasErrors()) {
            flash.error = render("Failed to create1 : ${linkResourceCO.errors.allErrors}")
            render("Failed to create1 : ${linkResourceCO.errors.allErrors}")
        } else {
            LinkResource linkResource = new LinkResource(url: linkResourceCO.linkResourceLink, description: linkResourceCO.linkResourceComment,
                    topic: Topic.get(linkResourceCO.topicId), createdBy: session.user)
            if (linkResource.save()) {
                flash.message = "Link resource successfully save"
                addToReadingItems(linkResource)
                forward(controller: 'user', action: 'show')
            } else {
                flash.error = "Failed to create link resource"
            }
        }
    }
}
