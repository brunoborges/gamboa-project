package code.services

import code.services.email.{EmailSettings, EmailSenderService}
import javax.inject._
import javax.ejb._

trait ContactService {
  def send(contact: Map[String, String])
}

@Local
@Stateless
class ContactServiceImpl extends ContactService {

  // @Inject var emailSender: EmailSenderService = _

  // @Inject
  // @Qualifier("contactEmailSettings")
  //var emailSettings: EmailSettings = _

  def send(contact: Map[String, String]) = {}//emailSender.send(emailSettings, contact);

}
