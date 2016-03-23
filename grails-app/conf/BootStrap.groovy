import com.tothenew.DocumentResource
import com.tothenew.LinkResource
import com.tothenew.ReadingItem
import com.tothenew.Resource
import com.tothenew.Role
import com.tothenew.Subscription
import com.tothenew.Topic
import com.tothenew.ResourceRating
import com.tothenew.UserRole
import com.tothenew.constants.Constants
import com.tothenew.User
import com.tothenew.enums.Seriousness
import com.tothenew.enums.Visibility

class BootStrap {
    def grailsApplication
    List<User> users = []
    def init = { servletContext ->
        /*User user = new User(email: "v1@gmail.com",
                username: "vikas1",
                password: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                enabled: true).save()

        User admin =new User(email: "v2@gmail.com",
                username: "vikas2",
                password: Constants.PASSWORD,
                firstName: "vikas",
                lastName: "verma",
                enabled: true,
                admin: true).save()*/

         /*  if (!User.count()) {
                createUser(user)
                createUser(admin)
            }

            if (!Topic.count()) createTopics()
            if (!Resource.count()) createResources()
            subscribeTopics()
            createReadingItems()
            createResourceRatings()*/
     /*   Role roleUser= new Role(authority: "ROLE_NORMAL").save()
        Role roleAdmin= new Role(authority: "ROLE_ADMIN").save()

        UserRole.create(user,roleUser)
        UserRole.create(admin,roleUser)
        UserRole.create(admin,roleAdmin)*/

    }


    void createUser(User user) {
        saveObject(user)
    }

    void createTopics() {
        if (!users.size())
            users = User.list()
        users.each { user ->
            if (!Topic.countByCreatedBy(user)) {
                (1..5).each { i ->
                    log.info("i = ${i}")
                    Topic topic = new Topic(name: "topic${i}", createdBy: user, visibility: Visibility.PUBLIC)
                    user.addToTopics(topic)
                    saveObject(topic)
                }
            }
        }
    }


    void createResources() {
        List<Topic> topics = Topic.list();
        topics.each { topic ->
            2.times {
                Resource linkResource = new LinkResource(description: "${topic.name} desc", createdBy: topic.createdBy,
                        topic: topic, url: "http://www.tothenew.com/")
                Resource documentResource = new DocumentResource(description: "${topic.name} desc", createdBy: topic.createdBy,
                        topic: topic, filePath: "/home/vikas")
                if (saveObject(linkResource) && saveObject(documentResource)) {
                    topic.addToResources(documentResource)
                    topic.addToResources(linkResource)
                }
            }
        }
    }

    void subscribeTopics() {
        if (!users.size())
            users = User.list()
        List<Topic> topics = Topic.list()
        users.each { user ->
            topics.each { topic ->
                if (topic.createdBy != user) {
                    Subscription subscription = Subscription.findOrSaveWhere([user       : user, topic: topic,
                                                                              seriousness: Seriousness.VERY_SERIOUS])

                    if (subscription) {
                        user.addToSubscriptions(subscription)
                        log.info "Subscription ${subscription} saved successfully"
                    } else {
                        log.error "Error saving subscription : ${subscription.errors.allErrors}"
                    }
                }
            }
        }
    }


    void createReadingItems() {
        if (!users.size())
            users = User.list()
        List<Topic> topics = Topic.list()
        users.each { user ->
            topics.each { topic ->
                if (Subscription.findByUserAndTopic(user, topic)) {
                    topic.resources.each { resource ->
                        if (resource.createdBy != user && !user.readingItems?.contains(resource)) {
                            ReadingItem readingItem = new ReadingItem(user: user, resource: resource, isRead: false)
                            if (saveObject(readingItem)) {
                                user.addToReadingItems(readingItem)
                            }
                        }
                    }
                }
            }
        }
    }

    void createResourceRatings() {
        if (!users.size())
            users = User.list()

        users.each { user ->
            user.readingItems?.each { readingItem ->
                if (!readingItem.isRead) {
                    ResourceRating resourceRating = new ResourceRating(user: readingItem.user, resource: readingItem.resource,
                            score: (int) (Math.random() * 5) + 1)
                    if (saveObject(resourceRating)) {
                        user.addToResourceRatings(resourceRating)
                    }
                }
            }
        }
    }

    boolean saveObject(def obj) {
        if (obj.save(failOnError: true, flush: true)) {
            log.info "${obj} saved successfully"
            return true
        } else {
            log.error "Error saving : ${obj.errors.allErrors}"
            return false
        }
    }

    def destroy = {
    }
}
