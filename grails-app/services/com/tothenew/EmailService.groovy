package com.tothenew

import com.tothenew.dto.EmailDTO
import grails.transaction.Transactional

@Transactional

class EmailService {
    def mailService
    def messageSource

    def sendMail(EmailDTO emailDTO) {
        mailService.sendMail {
            to(emailDTO.to.toArray())
            subject(emailDTO.subject)
            if (emailDTO.content) {
                html(emailDTO.content)
            } else {
                html(view: emailDTO.view, model: emailDTO.model)
            }
        }
    }

    def sendUnreadResourcesEmail(User user, List<Resource> unreadResource) {
        EmailDTO emailDTO = new EmailDTO(to: [user.email],
                subject: messageSource.getMessage("com.tothenew.co.dto.EmailDTO.unread.subject", [].toArray(), Locale.default),
                view: "/email/unreadResources", model: [user: user, unreadResource: unreadResource])
        sendMail(emailDTO)
    }
}

