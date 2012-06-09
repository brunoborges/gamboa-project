package code.services.email;

import javax.mail.internet.MimeMessage
import scala.util.Random
import scala.reflect.BeanProperty
import javax.ejb.Stateless
import javax.inject.Inject
import javax.mail.Session
import javax.annotation.Resource
import javax.mail.Message.RecipientType
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.Transport

trait EmailSenderService {
  def send(settings: EmailSettings, to: String, values: Map[String, Object])
  def send(settings: EmailSettings, values: Map[String, Object])
}

@Stateless
class EmailSenderServiceImpl extends EmailSenderService {

  @Inject var templates: EmailTemplateEngine = _
  @Resource(name = "mail/groupbirthdays") var session: Session = _

  def send(settings: EmailSettings, values: Map[String, Object]) =
    send(settings, settings.to, values)

  def send(settings: EmailSettings, to: String, values: Map[String, Object]) {
    val msg = new MimeMessage(session)
    val ref = java.lang.Long.toString(Random.nextLong(), 16).substring(0, 7)

    msg.setSubject(settings.subject + " #" + ref)
    msg.setRecipient(RecipientType.TO, new InternetAddress(if (to == null) settings.to else to))
    msg.setFrom(new InternetAddress(settings.from, settings.person))

    val text = templates.transform(settings.template, values);

    if (text.contains("<html")) {
    	msg.setContent(text, "text/html")
    } else {
      msg.setContent(text, "text/plain")
    }

    Transport.send(msg)
  }

}
