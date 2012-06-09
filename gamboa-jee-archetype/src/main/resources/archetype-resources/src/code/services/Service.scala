package code.services

import code.services.email.{ EmailSettings, EmailSenderService }
import code.data.User
import scala.collection.JavaConversions._
import scala.collection.mutable.HashMap
import java.util.ArrayList
import javax.ejb.{ Stateless, LocalBean }
import javax.inject.{ Inject, Qualifier }
import javax.persistence.{ PersistenceContext, EntityManagerFactory, EntityManager }

trait Service {
  def update(user: User)
  def exists(email: String): Boolean
  def create(login: User): Boolean
  def authenticate(email: String, password: String): Option[User]
  def rememberPassword(email: String): Boolean
}

@Stateless
class ServiceImpl extends Service {

  // @Inject var emailSender: EmailSenderService = _
  // @Inject @Qualifier("signUpEmailSettings") var sues: EmailSettings = _
  // @Inject @Qualifier("rememberPassEmailSettings") var rpes: EmailSettings = _

  @PersistenceContext(name = "${artifactId}") 
  var em: EntityManager = _

  def exists(email: String): Boolean = {
    val obj = findUserByEmail(email)
    obj match {
      case Some(o) ⇒ true
      case None ⇒ false
    }
  }

  def create(user: User): Boolean = {
    em.persist(user)
    System.out.println("after create: "+user.getId())
    true
    // emailSender.send(sues, user.email, Map("name" -> user.name))
  }

  def authenticate(email: String, password: String): Option[User] = {
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

  def update(user: User) = { 
    System.out.println("before: "+user.getId())
    em.merge(user)
    System.out.println("after: "+user.getId())
  }

  def rememberPassword(email: String): Boolean = {
    val obj = findUserByEmail(email)
    obj match {
      case Some(o) ⇒ {
        //emailSender.send(rpes, o.email, Map("name" -> o.name, "email" -> o.email, "password" -> o.password))
        true
      }
      case None ⇒ false
    }
  }

  def findUserByEmail(email: String): Option[User] = {
    val query = em.createQuery("select u from User u where u.email = :email")
    query.setParameter("email", email)
    val results = query.getResultList()
    if (results.isEmpty()) {
      None
    } else {
      Some(results.get(0).asInstanceOf[User])
    }
  }

}
