package code.services

import email.EmailSettings
import email.EmailSenderService

import code.Record
import org.springframework.beans.factory.annotation.{ Qualifier, Autowired }
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl extends ContactService {

  @Autowired
  var emailSender: EmailSenderService = _

  @Autowired
  @Qualifier("contactEmailSettings")
  var emailSettings: EmailSettings = _

  def send(contact: Record) {
    emailSender.send(emailSettings, contact);
  }

}

