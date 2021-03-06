package com.tothenew

class ReadingItemController {

    def changeIsRead(Long id, Boolean isRead) {

        int result = ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where resource.id=:resourceId and user.id=:userId",
                [resourceId: id, isRead: isRead, userId: session.user.id])
        if (result) {
            flash.message = "Reading item successfully updated"
        } else {
            flash.error = "Reading item could not update successfully"
        }
        redirect(url: request.getHeader("referer"))
    }
}
