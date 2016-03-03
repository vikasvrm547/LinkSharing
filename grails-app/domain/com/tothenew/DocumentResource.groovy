package com.tothenew

class DocumentResource extends Resource {
    String filePath

    static constraints = {
        filePath(blank: false)
    }

    @Override
    String toString() {
        return "FilePath: $filePath"
    }
}
