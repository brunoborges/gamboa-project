package code.services

import code.services.email.{EmailSettings, EmailSenderService}
import javax.inject.Inject
import javax.inject.Qualifier
import javax.ejb.Stateless

trait ContactService {
  def send(contact: Map[String, String])
}

@Stateless
class ContactServiceImpl extends ContactService {

  // @Inject var emailSender: EmailSenderService = _

  // @Inject
  // @Qualifier("contactEmailSettings")
  //var emailSettings: EmailSettings = _

  def send(contact: Map[String, String]) = {}//emailSender.send(emailSettings, contact);

}
