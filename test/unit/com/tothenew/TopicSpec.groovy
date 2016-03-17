package com.tothenew

import com.tothenew.enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


@TestFor(Topic)
@Mock([Topic, User, Subscription,ReadingItem,Resource])
class TopicSpec extends Specification {


    @Unroll("check topic constraints sno---------#sno")
    def "check topic constraints"() {
        given:

        Topic topicObj = new Topic(name: topicName, createdBy: creator, visibility: visibility);

        when:
        boolean result = topicObj.validate();

        then:
        result == expected

        where:
        sno | topicName | creator    | visibility         | expected
        1   | "grails"  | new User() | Visibility.PRIVATE | true
        2   | " "       | new User() | Visibility.PRIVATE | false
        3   | null      | new User() | Visibility.PRIVATE | false
        4   | "grails"  | null       | Visibility.PRIVATE | false
        5   | "grails"  | new User() | null               | false
    }

    def "check user uniqueness"() {
        given:
        String topicName = "grails"
        User creator = new User();
        Visibility visibility = Visibility.PRIVATE;
        Topic topicObj = new Topic(name: topicName, createdBy: creator, visibility: visibility);

        when:
        topicObj.save();

        then:
        Topic.count() == 1;

        when:
        topicObj = new Topic(name: topicName, createdBy: creator, visibility: visibility);
        topicObj.save();

        then:
        Topic.count() == 1;
        topicObj.errors.allErrors.size() == 1;
        topicObj.errors.getFieldErrorCount('name') == 1;
    }

    def "Check toString of topic"() {
        setup:
        Topic topic = new Topic(name: name)

        when:
        String topicName = topic.toString()

        then:
        topicName == result

        where:
        name            | result
        "testTopicName" | "testTopicName"
    }

    @Unroll("Sno------------ #sno")
    def "check Visibility enum convertToEnum method"() {
        when:
        Visibility result = Visibility.convertToEnum(str)
        then:
        result == expected
        where:
        sno | str       | expected
        1   | "PUBLIC"  | Visibility.PUBLIC
        2   | "public"  | Visibility.PUBLIC
        3   | "PRIVATE" | Visibility.PRIVATE
        4   | "Private" | Visibility.PRIVATE
        6   | "vikas "  | null
        7   | " "       | null
    }

    @Unroll("Sno------------ #sno")
    def "check isPublic method"() {
        given:
        Topic topicObj = new Topic(name: "topic1", createdBy: new User(), visibility: visibility)
        when:
        Boolean result = topicObj.isPublic()
        then:
        result == expected
        where:
        sno | visibility         | expected
        1   | Visibility.PUBLIC  | true
        2   | Visibility.PRIVATE | false

    }

    @Unroll("Sno------------ #sno")
    def "check canViewedBy method for isPublic condition"() {
        given:
        String userName = "vikasvrm"
        String password = "vikas12345"
        String confirmPassword = "vikas12345"
        String firstName = "vikas"
        String lastName = "verma"
        String email = "vikas@gmail.com"

        User user1 = new User(userName: userName, email: email, password: password, confirmPassword: confirmPassword, firstName: firstName, lastName: lastName, admin: isAdmin)
        user1.save(flush: true)
        User user2 = new User(userName: "asd", email: "asd@asd.com", password: password, confirmPassword: confirmPassword, firstName: firstName, lastName: lastName, admin: isAdmin)
        user2.save(flush: true)
        Topic topicObj = new Topic(name: "topic1", createdBy: User.get(2), visibility: visibility)
        topicObj.save(flush: true)
        when:
        Boolean result = Topic.get(1).canViewedBy(User.get(userId))
        then:
        result == expected
        where:
        sno | visibility         | userId | isAdmin | expected
        1   | Visibility.PUBLIC  | 1      | false   | true
        2   | Visibility.PRIVATE | 2      | false   | true
        3   | Visibility.PRIVATE | 1      | true    | true
        4   | Visibility.PUBLIC  | 2      | true    | true
        5   | Visibility.PRIVATE | 1      | false   | false
    }
    def "check equals method with same reference"() {
        given:
        new Topic(createdBy: new User(),name: "t",visibility: Visibility.PRIVATE).save()
        Topic topic = Topic.get(1l)
        expect:
        topic.equals(topic) == true
    }

    def "check equals method with different reference and same id"() {
        given:
        new Topic(createdBy: new User(),name: "t",visibility: Visibility.PRIVATE).save()
        new Topic(createdBy: new User(),name: "t",visibility: Visibility.PRIVATE).save()
        expect:
        Topic.get(1l).equals(Topic.get(1l)) == true
    }

    def "check equals method with different reference and id"() {
        given:
        new Topic(createdBy: new User(),name: "t",visibility: Visibility.PRIVATE).save()
        new Topic(createdBy: new User(),name: "t",visibility: Visibility.PRIVATE).save()
        expect:
        Topic.get(1l).equals(Topic.get(2l)) == false
    }

    def "check getTopicPosts method"(){
        given:
        String userName = "vikasvrm"
        String password = "vikas12345"
        String firstName = "vikas"
        String lastName = "verma"
        String email = "vikas@gmail.com"
        String confirmPassword = "vikas12345"
        new User(userName: userName, email: email, password: password, confirmPassword: confirmPassword,
                firstName: firstName, lastName: lastName).save()
        Topic topic = new Topic(createdBy: new User(), name: "t", visibility: Visibility.PUBLIC).save()
        new LinkResource(description: "s1",createdBy: User.get(1l),
                url: 'http://www.tothenew.com/',topic: Topic.get(1l)).save()
        expect:
        topic.getTopicPosts().size() != 0
    }

    def "check hashCode method"(){
        expect:
        new Topic(createdBy: new User(), name: "t", visibility: Visibility.PUBLIC).save().hashCode() != 0
    }
    def "check hashCode method with 0 hashcode value"(){
        expect:
        new Topic().hashCode() == 0
    }
}
