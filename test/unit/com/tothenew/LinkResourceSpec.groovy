package com.tothenew

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([User, Topic, LinkResource])
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
        "http://www.tothenew.com" | "http://www.tothenew.com"
    }

    def "check equals method with same reference"() {
        given:
        new LinkResource(url: "https://www.google.co.in/", description: "d", createdBy: new User(),
                topic: new Topic()).save()
        LinkResource linkResource = LinkResource.get(1l)
        expect:
        linkResource.equals(linkResource) == true
    }

    def "check equals method with different reference and same id"() {
        given:
        new LinkResource(url: "https://www.google.co.in/", description: "d", createdBy: new User(),
                topic: new Topic()).save()
        new LinkResource(url: "https://www.google.co.in/", description: "d", createdBy: new User(),
                topic: new Topic()).save()
        expect:
        LinkResource.get(1l).equals(LinkResource.get(1l)) == true
    }

    def "check equals method with different reference and id"() {
        given:
        new LinkResource(url: "https://www.google.co.in/", description: "d", createdBy: new User(),
                topic: new Topic()).save()
        new LinkResource(url: "https://www.google.co.in/", description: "d", createdBy: new User(),
                topic: new Topic()).save()
        expect:
        LinkResource.get(1l).equals(LinkResource.get(2l)) == false
    }

    def "check hashCode method"() {
        expect:
        new LinkResource(url: "https://www.google.co.in/", description: "d", createdBy: new User(),
                topic: new Topic()).save().hashCode() != 0
    }

    def "check hashCode method with 0 hashcode value"() {
        expect:
        new LinkResource().hashCode() == 0
    }
}


