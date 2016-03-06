package com.tothenew

class ApplicationFilters {

    def filters = {

        all(controller: '*', action: '*') {
            before = {
                log.info("Going to $controllerName -> $actionName")
            }
        }
    }

}
