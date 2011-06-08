package code.services.email

import org.springframework.mail.javamail.JavaMailSender
import scala.reflect.BeanProperty

class EmailSettings {

  @BeanProperty
  var sender: JavaMailSender = _

  @BeanProperty
  var from: String = _

  @BeanProperty
  var to: String = _

  @BeanProperty
  var subject: String = _

  @BeanProperty
  var person: String = _

  @BeanProperty
  var template: String = _

}
