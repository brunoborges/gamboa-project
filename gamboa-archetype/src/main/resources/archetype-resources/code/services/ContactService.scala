package code.services

import scala.collection.mutable.HashMap
import com.mongodb.casbah.commons.MongoDBObject
import email.{ EmailSettings, EmailSenderService }

import org.springframework.beans.factory.annotation.{ Qualifier, Autowired }
import org.springframework.stereotype.Service

trait ContactService {
  def send(contact: MongoDBObject)
}

@Service
class ContactServiceImpl extends ContactService {

  @Autowired
  var emailSender: EmailSenderService = _

  @Autowired
  @Qualifier("contactEmailSettings")
  var emailSettings: EmailSettings = _

  def send(contact: MongoDBObject) {
    emailSender.send(emailSettings, contact.asInstanceOf[Map[String, Object]]);
  }

}
