package com.tothenew

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
        1   | "description" | new User() | new Topic() | '/ home/vikas' | true
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
        "home/vikas" | "FilePath: home/vikas"
    }
}
