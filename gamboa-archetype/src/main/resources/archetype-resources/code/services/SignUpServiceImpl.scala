package code.services

import email.EmailSenderService
import email.EmailSettings
import code.Record
import com.mongodb.{ DBObject, DBCollection, DB, BasicDBObjectBuilder }
import java.util.{ Map, HashMap }
import org.springframework.beans.factory.annotation.{ Qualifier, Autowired }
import org.springframework.stereotype.Service

@Service
class SignUpServiceImpl extends SignUpService {

  @Autowired
  var emailSender: EmailSenderService = _

  @Autowired
  @Qualifier("signUpEmailSettings")
  var emailSettings: EmailSettings = _

  @Autowired
  var db: DB = _

  def getCollection(): DBCollection = db.getCollection("users")

  def signUp(user: Record) {
    val dbo = BasicDBObjectBuilder.start(user).get()

    getCollection().insert(dbo)

    val map = new HashMap[String, Object]()
    map.put("user", user)

    emailSender.send(emailSettings, user.get("email").toString(), map)
  }

  def loadUser(email: String): Record = {
    val dbo = BasicDBObjectBuilder.start("email", email).get()
    val col = getCollection()
    val dboFound = col.findOne(dbo)

    if (dboFound == null) {
      null;
    }

    new Record(dboFound.toMap().asInstanceOf[Map[String, Object]])
  }

  def emailExists(email: String): Boolean = {
    val dbo = BasicDBObjectBuilder.start("email", email).get();
    val col = getCollection();
    col.count(dbo) > 0;
  }

}
