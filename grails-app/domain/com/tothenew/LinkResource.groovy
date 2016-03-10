package com.tothenew

import grails.transaction.Transactional

class LinkResource extends Resource {
    String url

    static constraints = {
        url(url: true, blank: true)
    }


    @Override
    Boolean deleteFile(){

         this.delete(flush: true)
        return true
    }
    @Override
    String toString() {
        return url
    }
}
