package com.tothenew

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


@TestFor(Topic)
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
}
