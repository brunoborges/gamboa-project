package code.services.email;

import org.springframework.stereotype.Service
import scala.util.Random
import javax.mail.internet.MimeMessage
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.beans.factory.annotation.Autowired
import scala.reflect.BeanProperty
import org.springframework.mail.javamail.JavaMailSender

@Service
class EmailSenderServiceImpl extends EmailSenderService {

  @Autowired
  @BeanProperty
  var templates: EmailTemplateEngine = _

  def send(settings: EmailSettings, values: java.util.Map[String, Object]) =
    send(settings, settings.to, values)

  @Async
  def send(settings: EmailSettings, to: String, values: java.util.Map[String, Object]) {
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

