package com.tothenew

class SessionCheckFilters {

    def filters = {
        /*userShowLoginCheck(uri:"/user/show") {
            before = {
                if (!session.user) {
                    redirect(controller: 'login', action: 'index')
                }
            }
        }*/

        /*readingItemLoginCheck(uri:"/readingItem/save") {
            before = checkSession
        }
        resourceDeleteLoginCheck(uri:"/resource/delete") {
            before = checkSession
        }
        userShowLoginCheck(uri:"/resourceRating/save") {
            before = checkSession
        }
        subscriptionSaveLoginCheck(uri:"/subscription*//**") {
         before = checkSession
         }
         linkResourceSaveLoginCheck(uri:"/linkResource/save") {
         before = checkSession
         }
         topicSaveLoginCheck(uri:"/topic/save") {
         before = checkSession
         }*/
    }

}
