package code.webapp

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import org.apache.wicket.request.Request
import code.data.User
import code.services.Service
import org.apache.wicket.Session
import javax.inject.Inject

class MySession(request: Request) extends AuthenticatedWebSession(request) {

  @Inject var service: Service = _

  var user: User = _
  val roles = new Roles()

  override def getRoles(): Roles = roles
  override def authenticate(email: String, password: String): Boolean = {
    var authenticated = false
    val gbUser = service.authenticate(email, password)
    gbUser match {
      case Some(g) ⇒ {
        user = g
        roles.add("USER")
        authenticated = true
      }
      case None ⇒ authenticated = false
    }
    authenticated
  }
  
  def update() {
    service.update(user)
  }

}

object MySession {
  def get = Session.get().asInstanceOf[MySession]
}
