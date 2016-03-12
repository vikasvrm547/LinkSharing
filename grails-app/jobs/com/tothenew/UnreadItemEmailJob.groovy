package com.tothenew



class UnreadItemEmailJob {
    def userService
    static triggers = {
        cron name: 'myTrigger', cronExpression: "0 0 1 ? * MON"
    }

    def execute() {
      userService.sendUnreadItemsEmail()
    }
}
