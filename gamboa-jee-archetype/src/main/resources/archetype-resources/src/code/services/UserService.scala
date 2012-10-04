package code.services

import code.services.email.{ EmailSettings, EmailSenderService }
import code.services.email.settings.{SignUpSettings, RememberPasswordSettings}
import code.data.User
import scala.collection.JavaConversions._
import scala.collection.mutable.HashMap
import java.util.ArrayList
import javax.ejb.Stateless
import javax.inject.Inject
import javax.persistence.{ PersistenceContext, EntityManagerFactory, EntityManager }

trait UserService {
  def update(user: User)
  def exists(email: String): Boolean
  def create(login: User): Boolean
  def authenticate(email: String, password: String): Option[User]
  def rememberPassword(email: String): Boolean
}

@Stateless
class UserServiceImpl extends UserService {

  @Inject var emailSender: EmailSenderService = _

  @PersistenceContext var em: EntityManager = _

  def exists(email: String): Boolean =
    findUserByEmail(email) match {
      case Some(o) ⇒ true
      case None ⇒ false
    }

  def create(user: User): Boolean = {
    em.persist(user)
    emailSender.send(SignUpSettings, user.email, Map("name" -> user.email))
    true
  }

  def authenticate(email: String, password: String): Option[User] = {
    findUserByEmail(email) match {
      case Some(g) ⇒
        g.getPassword().equals(password) match {
          case true ⇒ Some(g)
          case false ⇒ None
        }
      case None ⇒ None
    }
  }

  def update(user: User) = em.merge(user)

  def rememberPassword(email: String): Boolean = {
    findUserByEmail(email) match {
      case Some(o) ⇒ {
        emailSender.send(RememberPasswordSettings, o.email, Map("name" -> o.email, "email" -> o.email, "password" -> o.password))
        true
      }
      case None ⇒ false
    }
  }

  def findUserByEmail(email: String): Option[User] = {
    val results = em
      .createQuery("select u from User u where u.email = :email")
      .setParameter("email", email)
      .getResultList()

    results.isEmpty() match {
      case true ⇒ None
      case false ⇒ Some(results.get(0).asInstanceOf[User])
    }
  }

}