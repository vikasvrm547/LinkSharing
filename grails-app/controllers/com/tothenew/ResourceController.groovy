package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.enums.Visibility
import com.tothenew.vo.PostVO

class ResourceController {

    def search(ResourceSearchCO resourceSearchCO) {
        String result = ""
        List<PostVO> postVOList = []
        if (resourceSearchCO.q) {
            postVOList = Resource.search(resourceSearchCO).list().collect({
                Resource.getPost(it.id)
            })
        }
        if (resourceSearchCO.visibility == Visibility.PUBLIC) {
            render(view: 'search', model: [postVOList: postVOList, trendingTopics: Topic?.getTrendingTopics()])
        } else {
            postVOList.each {
                result += g.render(template: g.createLink(controller: 'resource', action: 'show'), model: [post: it])
            }
            if (postVOList.size() == 0) {
                result = "<h1>No resource found<h1>"
            }
            render(result)
        }
    }

    def show(Long resourceId) {

        User currentUser = session.user
        Resource resource = Resource.findById(resourceId)
        if (resource) {
            if (resource.topic.canViewedBy(currentUser)) {
                PostVO postVO = Resource?.getPost(resourceId);
                Integer score = currentUser?.getScore(resourceId)
                render(view: 'show', model: [postVO: postVO, currentUser: currentUser,
                                             score : score, tendingTopics: Topic?.getTrendingTopics()])
            } else {
                flash.error = "You cannot vieww this topic"
                redirect(url: request.getHeader("referer"))
            }
        } else {
            flash.error = "Resource not found"
            redirect(url: request.getHeader("referer"))
        }
    }


    def delete(Long id) {
        if (session.user.canDeleteResource(id)) {
            Resource resource = Resource.load(id);
            if (resource) {
                try {
                    if (resource.deleteFile())
                        flash.message = "Resource successfully deleted"
                    else
                        flash.error = "Resource could not delete successfully"
                } catch (Exception e) {
                    flash.error = "Resource not found"
                }
                finally {
                    redirect(controller: 'login', action: 'index')
                }
            }
        }
    }

    def update(Long resourceId, String description) {
        if (Resource.updateDescription(resourceId, description)) {
            flash.message = "Successfully update description"
        } else {
            flash.error = "Failed to update description "
        }
        redirect(url: request.getHeader("referer"))
    }

    void addToReadingItems(Resource resource) {
        Topic topic = Resource.createCriteria().get {
            projections {
                property('topic')
            }
            eq('topic', resource.topic)
        }
        List<User> subscribedUserList = topic.getSubscribedUsers()
        subscribedUserList.each { user ->
            if (user.id == session.user?.id)
                resource.addToReadingItems(new ReadingItem(user: user, resource: resource, isRead: true).save())
            else
                resource.addToReadingItems(new ReadingItem(user: user, resource: resource, isRead: false).save())
        }
    }
}
