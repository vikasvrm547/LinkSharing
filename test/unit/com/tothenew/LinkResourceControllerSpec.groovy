package com.tothenew

import com.tothenew.co.LinkResourceCO
import com.tothenew.enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders
import spock.lang.Specification
import com.tothenew.User;

@Mock([Topic, LinkResource])
@TestFor(LinkResourceController)
class LinkResourceControllerSpec extends Specification {

    void "check save action with has error"() {
        given:
        LinkResourceCO linkResourceCO = new LinkResourceCO()
        linkResourceCO.metaClass.hasErrors = { true }
        when:
        controller.save(linkResourceCO)
        then:
        flash.error == "Failed to create link resource"
        response.redirectedUrl != null
    }

    void "check save action with save false"() {
        given:
        LinkResourceCO linkResourceCO = new LinkResourceCO()

        linkResourceCO.metaClass.hasErrors = { false }
        linkResourceCO.metaClass.save = { false }
        when:
        controller.save(linkResourceCO)
        then:
        flash.error == "Failed to create link resource"
        response.redirectedUrl != null
    }

    /*void "check save action with save true"() {
        given:
        LinkResourceCO linkResourceCO = new LinkResourceCO(linkResourceLink: "http://www.tothenew.com/",
                linkResourceComment: "sd",topicId: 1l)
        session.user = new User();
        new Topic(name: "topic", createdBy: new User(), visibility: Visibility.PUBLIC).save()

        linkResourceCO.metaClass.hasErrors = { false }

        when:
        controller.save(linkResourceCO)
        then:
        flash.message == "Link resource successfully save"

    }*/
}
