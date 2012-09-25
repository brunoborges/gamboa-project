package code.webapp

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import org.apache.wicket.request.Request

import code.data.User
import code.services.UserService
import javax.inject.Inject
import javax.persistence.Entity

class Session(request: Request) extends AuthenticatedWebSession(request) {

  @Inject var service: UserService = _

  var user: User = _
  val roles = new Roles()

  def update() = service.update(user)

  override def getRoles(): Roles = roles

  override def authenticate(email: String, password: String): Boolean = {
    val gbUser = service.authenticate(email, password)
    gbUser match {
      case Some(g) ⇒ {
        user = g
        roles.add("USER")
        return true
      }
      case None ⇒ return false
    }
  }

}

object Session {
  def get = org.apache.wicket.Session.get().asInstanceOf[Session]
}