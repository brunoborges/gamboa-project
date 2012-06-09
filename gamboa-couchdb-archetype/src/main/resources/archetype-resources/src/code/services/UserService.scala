package code.services

import code.data.User
import code.util.JSONImports._
import code.services.email.EmailSettings
import code.services.email.EmailSenderService
import com.fourspaces.couchdb.{ Document, Database }
import java.util.ArrayList
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.collection.JavaConversions._
import scala.collection.mutable.HashMap
import net.sf.json.JSONObject
import net.sf.ezmorph.bean.BeanMorpher
import net.sf.json.util.JSONUtils

trait UserService extends CouchDBService {
  def update(user: User)
  def exists(email: String): Boolean
  def create(login: User): Boolean
  def authenticate(email: String, password: String): Option[User]
  def rememberPassword(email: String): Boolean
}

@Service
class UserServiceImpl extends UserService {

  @Autowired var emailSender: EmailSenderService = _
  @Autowired @Qualifier("signUpEmailSettings") var sues: EmailSettings = _
  @Autowired @Qualifier("rememberPassEmailSettings") var rpes: EmailSettings = _

  override def exists(email: String): Boolean = {
    val obj = findUserByEmail(email)
    obj match {
      case Some(o) ⇒ true
      case None    ⇒ false
    }
  }

  override def create(user: User): Boolean = {
    val docuser = new Document(user)
    couchDB.saveDocument(docuser)
    emailSender.send(sues, user.email, Map("name" -> user.name))
    docuser.getId() != null
  }

  override def authenticate(email: String, password: String): Option[User] = {
    val existingUser = findUserByEmail(email)
    existingUser match {
      case Some(g) ⇒ {
        if (g.getPassword().equals(password)) {
          Some(g)
        } else {
          None
        }
      }
      case None ⇒ None
    }
  }

  override def update(user: User) = {
    val currDoc = couchDB.getDocument(user.getId())
    val docuser = new Document(user)
    docuser.setRev(currDoc.getRev())
    couchDB.saveDocument(docuser, user.getId())
    docuser.getId() != null
  }

  override def rememberPassword(email: String): Boolean = {
    val obj = findUserByEmail(email)
    obj match {
      case Some(o) ⇒ {
        emailSender.send(rpes, o.email, Map("name" -> o.name, "email" -> o.email, "password" -> o.password))
        true
      }
      case None ⇒ false
    }
  }

  private def findUserByEmail(email: String): Option[User] = {
    val query = """function (doc) {
                     if (doc.email=='%1$s') {
                       emit(null, doc);
                     }
                   }""" format (email)
    val view = couchDB.adhoc(query);
    if (!view.getResults().isEmpty()) {
      val doc = view.getResults().get(0)
      val jsonValue = doc.getJSONObject().getJSONObject("value")
      val myBean = JSONObject.toBean(jsonValue, classOf[User], new java.util.HashMap()).asInstanceOf[User]
      myBean.setId(doc.getId())
      Some(myBean)
    } else {
      None
    }
  }
}
