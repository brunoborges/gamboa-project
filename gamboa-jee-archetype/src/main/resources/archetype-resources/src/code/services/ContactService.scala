package code.services

import code.services.email.{EmailSettings, EmailSenderService}
import code.services.email.settings.ContactSettings
import jakarta.inject._
import jakarta.ejb._

trait ContactService {
  def send(contact: Map[String, String])
}

@Local
@Stateless
class ContactServiceImpl extends ContactService {

  @Inject var emailSender: EmailSenderService = _

  def send(contact: Map[String, String]) = emailSender.send(ContactSettings, contact);

}
