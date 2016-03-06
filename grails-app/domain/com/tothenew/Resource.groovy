package com.tothenew

import com.tothenew.co.ResourceSearchCo
import com.tothenew.enums.Visibility
import com.tothenew.vo.PostVO
import com.tothenew.vo.RatingInfoVO

abstract class Resource {
    String description
    User createdBy
    Date dateCreated
    Date lastUpdated

    static belongsTo = [topic: Topic]

    static transients = ['ratingInfo']

    static mapping = {
        description(type: 'text')
    }

    static constraints = {
        description(blank: false)
    }

    static hasMany = [readingItems: ReadingItem, ratings: ResourceRating]

    static namedQueries = {
        search { ResourceSearchCo resourceSearchCo ->
            if (resourceSearchCo.topicId) {
                eq('topic.id', resourceSearchCo.topicId)
            }
            if (resourceSearchCo.visibility) {
                eq('topic.visibility', resourceSearchCo.visibility)
            }
        }
    }

    RatingInfoVO getRatingInfo() {
        def criteria = ResourceRating.createCriteria()

        List<Long> result = criteria.get {
            projections {
                sum('score')
                count('score')
                avg('score')
            }
            eq('resource', this)
        }
        return new RatingInfoVO(totalScore: result[0], totalVotes: result[1], averageScore: result[2])
    }

    public static List<Resource> getRecentShares() {

        return Resource.createCriteria().list(max: 5, sort: 'lastUpdated', order: 'desc') {
            createCriteria('topic', 't')
            eq('t.visibility', Visibility.PUBLIC)
        }

    }

    public static List<Resource> getTopPosts() {
        def result = ResourceRating.createCriteria().list(max: 5) {
            createAlias('resource', 'r')
            createAlias('r.topic', 't')
            eq('t.visibility', Visibility.PUBLIC)
            avg('score', 'rating')
            groupProperty('r.id')
            order('rating', 'desc')
        }
        List list = result.collect { it[0] }
        return Resource.getAll(list)
    }

    static String checkResourceType(Long id) {
        Resource resource = Resource.get(id)
        if (resource.class.toString().equals("class com.tothenew.LinkResource")) {
            return "LinkResource"
        } else if (resource.class.toString().equals("class com.tothenew.DocumentResource")) {
            return "DocumentResource"
        } else {
            return ""
        }
    }

     PostVO getPost(Long resourceId,Long userId) {

        def post = ResourceRating.createCriteria().get{
            projections {
                property('resource.id')
                property('score')
            }
            resource {
                property('description')
                property('url')
                property('filePath')
                eq('id', resourceId)
                readingItems{
                    property('isRead')
                }
                topic {
                    property('name')
                    property('id')
                }
                createdBy {
                    property('userName')
                    property('firstName')
                    property('lastName')
                    property('photo')
                }
            }
            if(userId > 0)
                eq('user.id',userId)
        }
        return new PostVO(resourceID: post[0], resourceRating: post[1], description: post[2], url: post[3], filePath: post[4],
                isRead: post[5], topicName: post[6],topicId: post[7], userUserName: post[8], userFirstName: post[9],
                userLastName: post[10], userPhoto: post[11])
    }

    Boolean deleteFile(){
        log.info("This will be implemented in linkresource")
        return false
    }

    Boolean canViewBy(){

    }

}
