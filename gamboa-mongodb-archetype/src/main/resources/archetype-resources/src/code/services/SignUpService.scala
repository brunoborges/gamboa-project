package code.services

import email.{ EmailSenderService, EmailSettings }
import org.springframework.beans.factory.annotation.{ Autowired, Qualifier }
import org.springframework.stereotype.Service

trait SignUpService {
  def signUp(newUser: Map[String, String]): Unit
}

@Service
class SignUpServiceImpl extends SignUpService {

  @Autowired
  var emailSender: EmailSenderService = _

  @Autowired
  var userService: UserService = _

  @Autowired
  @Qualifier("signUpEmailSettings")
  var emailSettings: EmailSettings = _

  def signUp(user: Map[String, String]): Unit = {
    val defaultRole = if (userService.query(Map("role" -> "ADMIN")).nonEmpty) "USER" else "ADMIN"

    val newUser = user + ("role" -> defaultRole)
    userService += newUser.asInstanceOf[Map[String, Any]]
    emailSender.send(emailSettings, user("email"), Map("user" -> newUser))
  }

}
