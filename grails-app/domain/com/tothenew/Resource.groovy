package com.tothenew

import com.tothenew.co.ResourceSearchCo
import com.tothenew.enums.Visibility
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
            avg('id', 'totalVotes')
            groupProperty('r.id')
            order('totalVotes', 'desc')
        }
        List list = result.collect { it[0] }
        return Resource.getAll(list)
    }
}
