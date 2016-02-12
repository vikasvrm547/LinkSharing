package com.tothenew

import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {


    def "check constraints of resource rating excluding user uniqueness"() {
        given:
        ResourceRating resourceRatingObj = new ResourceRating(resource: resource, user: user, score: score);

        when:
        boolean result = resourceRatingObj.validate();

        then:
        result == excepted

        where:
        sno | resource           | user       | score | excepted
        1   | new LinkResource() | new User() | 4     | true
        2   | null               | new User() | 4     | false
        3   | new LinkResource() | null       | 4     | false
        4   | new LinkResource() | new User() | 9     | false
        5   | new LinkResource() | new User() | 0     | false
    }

    def "check user uniqueness"() {
        given:
        Resource resource = new LinkResource();
        User user = new User();
        int score = 4;
        ResourceRating resourceRatingObj = new ResourceRating(resource: resource, user: user, score: score);

        when:
        resourceRatingObj.save();

        then:
        ResourceRating.count() == 1;

        when:
        resourceRatingObj = new ResourceRating(resource: resource, user: user, score: score);
        resourceRatingObj.save();

        then:
        ResourceRating.count() == 1;
        resourceRatingObj.errors.allErrors.size() == 1;
        resourceRatingObj.errors.getFieldErrorCount('user') == 1;
    }
}
