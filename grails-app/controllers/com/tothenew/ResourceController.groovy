package com.tothenew

import com.tothenew.co.LinkResourceCO
import com.tothenew.co.ResourceSearchCo
import com.tothenew.enums.Visibility

class ResourceController {

    def search(ResourceSearchCo resourceSearchCo) {
        if (resourceSearchCo.q) {
            resourceSearchCo.visibility = Visibility.PUBLIC
        }
    }

    def show(Long id) {
        Resource resource = Resource.get(id)
        if (resource) {
            render(resource.getRatingInfo())
        } else {
            render("Resource not found")
        }
    }

    def saveLinkResource(LinkResourceCO linkResourceCO) {

        if (linkResourceCO?.hasErrors()) {
            // render(view: '/user/show', model: [linkResourceCO:linkResourceCO,tendingTopics:Topic.getTrendingTopics(), subscribedTopics:User.getSubscribedTopics(session.user.id)])
            flash.error =  render("Failed to create1 : ${linkResourceCO.errors.allErrors}")
            render("Failed to create1 : ${linkResourceCO.errors.allErrors}")
        } else {
            Resource resource = new LinkResource(url: linkResourceCO.linkResourceLink, description: linkResourceCO.linkResourceComment,
                    topic: Topic.get(linkResourceCO.linkResourceTopicId),createdBy: session.user)
            if(resource.save()){
                flash.message="Link resource successfully save"
                forward(controller: 'user',action: 'show')
            }else {
                flash.error =  "Failed to create2 : ${resource.errors.allErrors}"
                render("Failed to create2 : ${resource.errors.allErrors}")
            }

        }
    }


}
