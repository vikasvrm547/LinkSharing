package com.tothenew

class SessionCheckFilters {

    def filters = {
        loginCheck(controller: '*', action: 'save|delete|update|changeIsRead|join|invite') {
            before = {
                if (!session.user)
                    redirect(controller: "login", action: "index")
            }

        }

        userIndexcheck(controller: 'user', action: 'show|toggleActive|edit|updatePassword') {
            before = {

                if (!session.user)
                    redirect(controller: "login", action: "index")
            }
        }


        consoleCheck(controller: "console", action: "*"){
            before = {

                if(!(session.user?.admin))
                    redirect(controller: "login", action: "index")
            }
        }


    }


}
