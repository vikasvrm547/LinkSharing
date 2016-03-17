package com.tothenew

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([ReadingItem, User])
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

    def "check tostring method"() {
        given:
        ReadingItem readingItem = new ReadingItem(user: new User(userName: "vikas"), isRead: true)
        expect:
        readingItem.toString() == "ReadingItem{user=vikas, isRead=true}"
    }

    def "check equals method with same reference"() {
        given:
        new ReadingItem(user: new User(),isRead: true,resource:  new LinkResource()).save()
        ReadingItem readingItem = ReadingItem.get(1l)
        expect:
        readingItem.equals(readingItem) == true
    }

    def "check equals method with different reference and same id"() {
        given:
        new ReadingItem(user: new User(),isRead: true,resource:  new LinkResource()).save()
        new ReadingItem(user: new User(),isRead: true,resource:  new LinkResource()).save()
        expect:
        ReadingItem.get(1l).equals(ReadingItem.get(1l)) == true
    }

    def "check equals method with different reference and id"() {
        given:
        new ReadingItem(user: new User(),isRead: true,resource:  new LinkResource()).save()
        new ReadingItem(user: new User(),isRead: true,resource:  new LinkResource()).save()
        expect:
        ReadingItem.get(1l).equals(ReadingItem.get(2l)) == false
    }
    def "check hashCode method"(){
        expect:
        new ReadingItem(user: new User(),isRead: true,resource:  new LinkResource()).save().hashCode() != 0
    }
    def "check hashCode method with 0 hashcode value"(){
        expect:
        new ReadingItem().hashCode() == 0
    }
}
