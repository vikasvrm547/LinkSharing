class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
            }
        }

        "/"(controller: 'login', action: 'index')
        "500"(view:'error/500Error')
        "404"(view: "/error/404Error")
    }
}
