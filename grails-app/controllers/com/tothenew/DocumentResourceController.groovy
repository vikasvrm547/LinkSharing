package com.tothenew

import com.tothenew.constants.Constants
import grails.transaction.Transactional

class DocumentResourceController extends ResourceController {
    def springSecurityService
    @Transactional
    def save() {
        def file = params.file
        if (file.empty) {
            flash.error = 'file cannot be empty'
        } else {
            Topic topic = Topic.get(params.int('topicId'))
            if (topic) {
                saveDocumentResource(params, topic)
            } else {
                flash.error = "Topic is not valid"
            }
        }
        redirect(url: request.getHeader("referer"))

    }

    void saveDocumentResource(def params, Topic topic) {
        User currentUser = springSecurityService.getCurrentUser()
        String path = "/home/vikas${grailsApplication.config.linksharing.documents.folderPath}/${UUID.randomUUID()}.pdf"
        DocumentResource documentResource = new DocumentResource(description: params.comment, filePath: path,
                createdBy: currentUser, topic: topic, contentType: params.file.contentType)
        if (documentResource.save()) {
            File fileDest = new File(path)
            params.file.transferTo(fileDest)
            flash.message = "Document successfully created"

            def ctx = startAsync()
            ctx.start {
                addToReadingItems(documentResource)
                ctx.complete()
            }
        } else {
            flash.error = "validation failed"
        }
    }

    def download(Long resourceId) {
        User currentUser = springSecurityService.getCurrentUser()
        DocumentResource documentResource = (DocumentResource) Resource.get(resourceId)
        if (documentResource && documentResource.topic.canViewedBy(currentUser)) {
            def file = new File(documentResource.filePath)
            if (file.exists()) {
                response.setContentType(Constants.DOCUMENT_CONTENT_TYPE)
                response.setHeader("Content-disposition", "attachment;filename=\"${documentResource.getFileName()}\"")
                response.outputStream << file.bytes
            } else {
                flash.error = "resource not found"
            }
        } else {
            flash.error = "resource not found"
        }
        redirect(url: request.getHeader("referer"))
    }
}
