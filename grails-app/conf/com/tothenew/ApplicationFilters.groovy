package com.tothenew

class ApplicationFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                log.info("Going to $controllerName -> $actionName")
            }
            after = { Map model ->
                log.info("after")
                log.info(model)
            }
            afterView = { Exception e ->
                log.info("after view")
            }
        }

        loginCheck(controller: 'login', invert: true) {
            before = {
                if (!session.user) {
                    redirect(controller: 'login', action: 'index')
                }
            }
        }
    }
}
