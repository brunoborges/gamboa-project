package code.services

import scala.collection.mutable.HashMap
import com.mongodb.{ DBObject, DBCollection, DB, BasicDBObjectBuilder }
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.Imports._
import email.{ EmailSenderService, EmailSettings }
import org.springframework.beans.factory.annotation.{ Qualifier, Autowired }
import org.springframework.stereotype.Service
import scala.reflect.BeanProperty
import scala.collection.JavaConversions._

trait SignUpService {
  def signUp(newUser: Map[String, String])
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

  def signUp(user: Map[String, String]) {
	var defaultRole = "ADMIN"
    if (userService.query(Map("role" -> "ADMIN")).size > 0) {
      defaultRole = "USER"
    }

    val tmpUser = HashMap[String, String]("role" -> defaultRole) ++ user
    userService += tmpUser.toMap
    emailSender.send(emailSettings, user.get("email").get, Map("user" -> tmpUser))
  }

}
