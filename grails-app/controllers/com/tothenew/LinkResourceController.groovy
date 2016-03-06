package com.tothenew

import com.tothenew.co.LinkResourceCO

class LinkResourceController extends ResourceController{

    def save(LinkResourceCO linkResourceCO) {

        if (linkResourceCO?.hasErrors()) {
            flash.error = render("Failed to create1 : ${linkResourceCO.errors.allErrors}")
            render("Failed to create1 : ${linkResourceCO.errors.allErrors}")
        } else {
            Resource resource = new LinkResource(url: linkResourceCO.linkResourceLink, description: linkResourceCO.linkResourceComment,
                    topic: Topic.get(linkResourceCO.linkResourceTopicId), createdBy: session.user)
            if (resource.save()) {
                flash.message = "Link resource successfully save"
                forward(controller: 'user', action: 'show')
            } else {
                flash.error = "Failed to create2 : ${resource.errors.allErrors}"
                render("Failed to create2 : ${resource.errors.allErrors}")
            }
        }
    }
}
