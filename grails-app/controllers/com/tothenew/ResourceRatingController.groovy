package com.tothenew


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class ResourceRatingController {

    def save() {
        Resource resource = Resource.get(params.int('resourceId'))
        User user = User.get(params.int('userId'))
        if (user && resource) {
            ResourceRating resourceRating = ResourceRating.findOrCreateWhere(resource: resource,
                    user: user)
            if (resourceRating) {
                resourceRating.score = params.int('score')
                if (resourceRating.save(flush: true)) {
                    flash.message = 'Successfully save rating'
                    redirect(controller: 'resource', action: 'show', params: [resourceId: params.int('resourceId'),
                                                                              userId:params.int('userId') ])
                }
                else{
                    flash.error = 'Fail to save rating'
                }
            } else {
                flash.error = 'Fail to save rating'
            }
        }else {
            flash.error = 'Fail to save rating'
        }
        render(params)
    }
}