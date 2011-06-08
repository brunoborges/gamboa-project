package code.services.email;

import javax.mail.internet.MimeMessage
import org.springframework.stereotype.Service
import org.springframework.scheduling.annotation.Async
import org.springframework.mail.javamail.{ MimeMessageHelper, MimeMessagePreparator, JavaMailSender }
import org.springframework.beans.factory.annotation.Autowired
import scala.util.Random
import scala.reflect.BeanProperty

trait EmailSenderService {

  def send(settings: EmailSettings, to: String, values: Map[String, Object])

  def send(settings: EmailSettings, values: Map[String, Object])

}

@Service
class EmailSenderServiceImpl extends EmailSenderService {

  @Autowired
  @BeanProperty
  var templates: EmailTemplateEngine = _

  @Async
  def send(settings: EmailSettings, values: Map[String, Object]) =
    send(settings, settings.to, values)

  @Async
  def send(settings: EmailSettings, to: String, values: Map[String, Object]) {
    object messagePreparator extends MimeMessagePreparator {
      def prepare(mimeMessage: MimeMessage) {
        val message = new MimeMessageHelper(mimeMessage);
        val ref = java.lang.Long.toString(Random.nextLong(), 16).substring(0, 7)

        message.setSubject(settings.subject + " #" + ref);
        message.setTo(settings.to);
        message.setFrom(settings.from, settings.person);

        val text = templates.transform(settings.template, values);
        val hasHtml = text.contains("<html>");
        message.setText(text, hasHtml);
      }
    }

    settings.sender.send(messagePreparator)
  }

}
