package com.tothenew

import com.tothenew.constants.Constants
import grails.transaction.Transactional

class DocumentResource extends Resource {
    String filePath
    String fileName
    String contentType
    static transients = ['contentType', 'fileName']
    static constraints = {
        filePath(blank: false, bindable: true)
        contentType(bindable: true, blank: false, validator: { val, obj ->
            return val.equals(Constants.DOCUMENT_CONTENT_TYPE)
        })
    }

    String getFileName() {
        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length())
        return fileName ?: ""
    }

    @Transactional
    @Override
    Boolean deleteFile() {
        String filePath = createCriteria().get {
            projections {
                property('filePath')
            }
            eq('id', this.id)
        }
        boolean fileSuccessfullyDeleted = new File(filePath).delete()
        if (fileSuccessfullyDeleted) {
            this.delete()
            return true
        } else {
            return false
        }
    }

    @Override
    String toString() {
        return filePath
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        DocumentResource that = (DocumentResource) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
