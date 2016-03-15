package com.tothenew

import com.tothenew.co.ResourceSearchCO
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
        search {
            ResourceSearchCO resourceSearchCO ->
                if (resourceSearchCO.q) {
                    ilike('description', "%${resourceSearchCO.q}%")
                }
                if (resourceSearchCO.topicId) {
                    eq('topic.id', resourceSearchCO.topicId)
                }

                if (resourceSearchCO.visibility && resourceSearchCO.visibility == Visibility.PUBLIC) {
                    'topic' {
                        eq('visibility', Visibility.PUBLIC)

                    }
                }

                if (resourceSearchCO.id) {
                    eq('createdBy.id', resourceSearchCO.id)
                }
        }
        userResources {
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

    public List<PostVO> getTopicPosts() {
        List<PostVO> topicPosts = []
        Resource.createCriteria().list() {
            projections {
                property('id')
                property('description')
                property('url')
                property('filePath')
                topic {
                    property('id')
                    property('name')
                }
                createdBy {
                    property('id')
                    property('userName')
                    property('firstName')
                    property('lastName')
                }
                property('lastUpdated')
            }
            eq('topic.id', this.id)
            order('lastUpdated', 'desc')
        }.each {
            topicPosts.add(new PostVO(resourceID: it[0], description: it[1], url: it[2], filePath: it[3], topicId:
                    it[4], topicName: it[5], userId: it[6], userUserName: it[7], userFirstName: it[8], userLastName: it[9],
                    lastUpdated: it[10], isRead: ReadingItem.getIsRead(it[0], it[6])))
        }

        return topicPosts
    }

    public static List<Resource> getRecentShares() {
        List<PostVO> recentPosts = []
        Resource.createCriteria().list(max: 5, sort: 'lastUpdated', order: 'desc') {
            projections {
                property('id')
                property('description')
                property('url')
                property('filePath')
                topic {
                    property('id')
                    property('name')
                    eq('visibility', Visibility.PUBLIC)
                }
                createdBy {
                    property('id')
                    property('userName')
                    property('firstName')
                    property('lastName')
                }
                property('lastUpdated')
            }
            order('lastUpdated', 'desc')
        }.each {
            recentPosts.add(new PostVO(resourceID: it[0], description: it[1], url: it[2], filePath: it[3], topicId:
                    it[4], topicName: it[5], userId: it[6], userUserName: it[7], userFirstName: it[8], userLastName: it[9],
                    lastUpdated: it[10], isRead: ReadingItem.getIsRead(it[0], it[6])))
        }
        return recentPosts
    }

    public static List<PostVO> getTopPosts() {
        List<PostVO> topPosts = []
        ResourceRating.createCriteria().list(max: 5) {
            projections {
                property('resource.id')
                'resource' {
                    property('description')
                    property('url')
                    property('filePath')
                    'topic' {
                        property('id')
                        property('name')
                        eq('visibility', Visibility.PUBLIC)
                    }
                    'createdBy' {
                        property('id')
                        property('userName')
                        property('firstName')
                        property('lastName')
                    }
                    property('lastUpdated')
                }
            }
            groupProperty('resource.id')
            avg('score', 'rating')
            order('rating', 'desc')
            order('lastUpdated', 'desc')
        }?.each {
            topPosts.add(new PostVO(resourceID: it[0], description: it[1], url: it[2], filePath: it[3], topicId:
                    it[4], topicName: it[5], userId: it[6], userUserName: it[7], userFirstName: it[8], userLastName: it[9],
                    lastUpdated: it[10]))
        }
        return topPosts
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

    static PostVO getPost(Long resourceId) {

        List post = Resource.createCriteria().get {
            projections {
                property('id')
                property('description')
                property('url')
                property('filePath')
                'topic' {
                    property('id')
                    property('name')
                }
                'createdBy' {
                    property('id')
                    property('userName')
                    property('firstName')
                    property('lastName')
                }
                property('lastUpdated')
            }
            eq('id', resourceId)
        }

        return new PostVO(resourceID: post[0], score: 0, description: post[1], url: post[2], filePath: post[3],
                topicId: post[4], topicName: post[5], userId: post[6], userUserName: post[7], userFirstName: post[8],
                userLastName: post[9], lastUpdated: post[10])
    }


    Boolean deleteFile() {
        log.info("This will be implemented in linkresource")
        return false
    }

    static Integer updateDescription(Long resourceId, String description) {
        return executeUpdate("update Resource set description=:description where id=:id", [description: description, id: resourceId])
    }

    static List usersWithUnreadResources() {
        return ReadingItem.createCriteria().listDistinct {
            projections {
                property('user')
            }
            eq('isRead', false)
        }
    }
}
