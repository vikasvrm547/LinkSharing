class BootStrap {
    def grailsApplication;
    def init = { servletContext ->
        println(grailsApplication.config.grails.externalVariable)
    }
    def destroy = {
    }
}
