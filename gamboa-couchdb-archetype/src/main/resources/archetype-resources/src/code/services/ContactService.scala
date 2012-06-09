package code.services

import code.services.email.{ EmailSettings, EmailSenderService }
import org.springframework.beans.factory.annotation.{ Qualifier, Autowired }
import org.springframework.stereotype.Service
import scala.reflect.BeanProperty

trait ContactService {
  def send(contact: Map[String, String])
}

@Service
class ContactServiceImpl extends ContactService {

  @Autowired
  var emailSender: EmailSenderService = _

  @Autowired
  @Qualifier("contactEmailSettings")
  var emailSettings: EmailSettings = _

  def send(contact: Map[String, String]) = emailSender.send(emailSettings, contact);

}
