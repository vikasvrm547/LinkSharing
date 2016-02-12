package com.tothenew

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {

    def "check constraints of resource item excluding user uniqueness"() {
        given:
        ReadingItem resourceItemObj = new ReadingItem(resource: resource, user: user, isRead: isRead);

        when:
        boolean result = resourceItemObj.validate();

        then:
        result == excepted

        where:
        sno | resource           | user       | isRead | excepted
        1   | new LinkResource() | new User() | true   | true
        2   | null               | new User() | true   | false
        3   | new LinkResource() | null       | true   | false
        4   | new LinkResource() | new User() | null   | false
    }

    def "check user uniqueness"() {
        given:
        Resource resource = new LinkResource();
        User user = new User();
        Boolean isRead = false;
        ReadingItem readingItemObj = new ReadingItem(resource: resource, user: user, isRead: isRead);

        when:
        readingItemObj.save();
        then:
        ReadingItem.count() == 1;

        when:
        readingItemObj = new ReadingItem(resource: resource, user: user, isRead: isRead);
        readingItemObj.save();

        then:
        ReadingItem.count() == 1;
        readingItemObj.errors.allErrors.size() == 1;
        readingItemObj.errors.getFieldErrorCount('user') == 1;
    }
}
