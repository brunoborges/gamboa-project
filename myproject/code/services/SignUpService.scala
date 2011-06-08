package code.services

import scala.collection.immutable.HashMap
import com.mongodb.casbah.commons.MongoDBObject
import email.{ EmailSenderService, EmailSettings }
import com.mongodb.{ DBObject, DBCollection, DB, BasicDBObjectBuilder }
import org.springframework.beans.factory.annotation.{ Qualifier, Autowired }
import org.springframework.stereotype.Service

trait SignUpService {
  def signUp(name: String, email: String, password: String)
  def emailExists(email: String): Boolean
  def loadUser(email: String): MongoDBObject
  def listUsers(): List[MongoDBObject]
}

@Service
class SignUpServiceImpl extends AbstractService with SignUpService {

  @Autowired
  var emailSender: EmailSenderService = _

  @Autowired
  @Qualifier("signUpEmailSettings")
  var emailSettings: EmailSettings = _

  val dbCol = mongoConn("users")

  def signUp(name: String, email: String, password: String) {
    val dbo = MongoDBObject.newBuilder
    dbo += "name" -> name
    dbo += "email" -> email
    dbo += "password" -> password

    val obj = dbo.result
    dbCol += obj

    emailSender.send(emailSettings, obj.get("email").toString(), Map("user" -> obj))
  }

  def loadUser(email: String): MongoDBObject = {
    val dbo = BasicDBObjectBuilder.start("email", email).get()
    val dboFound = dbCol.findOne(dbo)
  }

  def emailExists(email: String): Boolean = {
    val dbo = BasicDBObjectBuilder.start("email", email).get();
    dbCol.count(dbo) > 0;
  }

  def listUsers(): List[MongoDBObject] = {
    null
  }
}
