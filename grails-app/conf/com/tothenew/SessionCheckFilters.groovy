package com.tothenew

class SessionCheckFilters {
    def springSecurityService
    def filters = {
        loginCheck(controller: '*', action: 'save|delete|update|changeIsRead|join|invite') {
            before = {
                if (!springSecurityService.isLoggedIn())
                    redirect(controller: "login", action: "index")
            }

        }

        userIndexcheck(controller: 'user', action: 'show|toggleActive|edit|updatePassword') {
            before = {

                if (!springSecurityService.isLoggedIn())
                    redirect(controller: "login", action: "index")
            }
        }


        consoleCheck(controller: "console", action: "*"){
            before = {

                if(!(springSecurityService.getCurrentUser()?.admin))
                    redirect(controller: "login", action: "index")
            }
        }


    }


}
