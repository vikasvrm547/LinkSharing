package com.tothenew

class ReadingItemController {

    def changeIsRead(Long id ,Boolean isRead) {
        int result = ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id",[id:id,isRead:isRead])
        if(result){
            render("Reading item successfully updated")
        }else {
            render("Reading item could not update successfully")
        }
    }
}
