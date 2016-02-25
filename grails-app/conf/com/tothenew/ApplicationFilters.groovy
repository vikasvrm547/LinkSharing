package com.tothenew

class ApplicationFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                log.info("Going to $controllerName -> $actionName")
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

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
