package com.tothenew

class DocumentResource extends Resource {
    String filePath;

    static constraints = {
        filePath(blank: false)
    }
}
