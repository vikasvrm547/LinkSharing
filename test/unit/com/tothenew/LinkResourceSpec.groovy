package com.tothenew

import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(LinkResource)
class LinkResourceSpec extends Specification {


    def "check constraints of link resources"() {
        given:
        LinkResource linkResourceObj = new LinkResource(description: description, createdBy: creator, topic: topic, url: url);

        when:
        boolean result = linkResourceObj.validate();

        then:
        result == excepted

        where:
        sno | description   | creator    | topic       | url                        | excepted
        1   | "description" | new User() | new Topic() | 'http://www.tothenew.com/' | true
        2   | " "           | new User() | new Topic() | 'http://www.tothenew.com/' | false
        3   | null          | new User() | new Topic() | 'http://www.tothenew.com/' | false
        4   | "description" | null       | new Topic() | 'http://www.tothenew.com/' | false
        5   | "description" | new User() | null        | 'http://www.tothenew.com/' | false
        6   | "description" | new User() | new Topic() | '://www.tothenew.com/'     | false
        7   | "description" | new User() | new Topic() | ' '                        | false
        8   | "description" | new User() | new Topic() | null                       | false

    }

    def "Check toString of linkResource"() {
        setup:
        LinkResource linkResource = new LinkResource(url: url)

        when:
        String resourceURL = linkResource.toString()

        then:
        resourceURL == result

        where:
        url                       | result
        "http://www.tothenew.com" | "URL: http://www.tothenew.com"
    }
}


