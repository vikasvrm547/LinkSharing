package com.tothenew

import grails.converters.JSON

class DemoController {
    def index() {
        render(grailsApplication.config.grails.externalVariable);
        log.fatal("I am fatal")
        log.error("I am error")
        log.warn("I am warn")
        log.info("I am info")
        log.debug("I am debug")
        log.trace("---------------------------------I am traces--------------------------------")
    }

    def jsonDemo(){
        Map m = ["1":"one","2":"two"]
        render(m as JSON)
    }

}
