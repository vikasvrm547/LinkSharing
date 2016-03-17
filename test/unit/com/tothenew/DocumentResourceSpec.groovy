package com.tothenew

import com.tothenew.constants.Constants
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    def "check constraints of document resources"() {
        given:
        DocumentResource documentResourceObj = new DocumentResource(description: description, createdBy: creator, topic: topic, filePath: filePath);

        when:
        boolean result = documentResourceObj.validate();

        then:
        result == excepted

        where:
        sno | description   | creator    | topic       | filePath       | excepted
        1   | "description" | new User() | new Topic() | '/ home/vikas' | false
        2   | " "           | new User() | new Topic() | '/home/vikas'  | false
        3   | null          | new User() | new Topic() | '/home/vikas'  | false
        4   | "description" | null       | new Topic() | '/home/vikas'  | false
        5   | "description" | new User() | null        | '/home/vikas'  | false
        6   | "description" | new User() | new Topic() | ' '            | false
        7   | "description" | new User() | new Topic() | null           | false

    }

    def "validating toString of documentResource"() {
        setup:
        DocumentResource documentResource = new DocumentResource(filePath: filePath)

        when:
        String documentPath = documentResource.toString()

        then:
        documentPath == result

        where:
        filePath     | result
        "home/vikas" | "home/vikas"
    }

    def "check equals method with same reference"() {
        given:
        new DocumentResource(filePath: "/a/a", contentType: Constants.DOCUMENT_CONTENT_TYPE, description: "d",
                createdBy: new User(), topic: new Topic()).save()
        DocumentResource documentResource = DocumentResource.get(1l)
        expect:
        documentResource.equals(documentResource) == true
    }

    def "check equals method with different reference and same id"() {
        given:
        new DocumentResource(filePath: "/a/a", contentType: Constants.DOCUMENT_CONTENT_TYPE, description: "d",
                createdBy: new User(), topic: new Topic()).save()
        new DocumentResource(filePath: "/a/a", contentType: Constants.DOCUMENT_CONTENT_TYPE, description: "d",
                createdBy: new User(), topic: new Topic()).save()
        expect:
        DocumentResource.get(1l).equals(DocumentResource.get(1l)) == true
    }

    def "check equals method with different reference and id"() {
        given:
        new DocumentResource(filePath: "/a/a", contentType: Constants.DOCUMENT_CONTENT_TYPE, description: "d",
                createdBy: new User(), topic: new Topic()).save()
        new DocumentResource(filePath: "/a/a", contentType: Constants.DOCUMENT_CONTENT_TYPE, description: "d",
                createdBy: new User(), topic: new Topic()).save()
        expect:
        DocumentResource.get(1l).equals(DocumentResource.get(2l)) == false
    }

    def "check hashCode method with some hashcode"() {
        expect:
        new DocumentResource(filePath: "/a/a", contentType: Constants.DOCUMENT_CONTENT_TYPE, description: "d",
                createdBy: new User(), topic: new Topic()).save().hashCode() != 0
    }

    def "check hashCode method with 0 hashcode value"() {
        expect:
        new DocumentResource().hashCode() == 0
    }
}
