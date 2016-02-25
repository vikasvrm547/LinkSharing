class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
            }
        }

        "/"(controller: 'login', action: 'index')
        "500"(view:'/error')
	}
}
