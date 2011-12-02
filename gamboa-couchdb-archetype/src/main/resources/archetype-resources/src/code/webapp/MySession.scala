package code.webapp

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import org.apache.wicket.injection.Injector
import org.apache.wicket.request.Request
import org.apache.wicket.spring.injection.annot.SpringBean
import code.data.User
import code.services.UserService
import org.apache.wicket.Session

class MySession(request: Request) extends AuthenticatedWebSession(request) {

  @SpringBean var service: UserService = _

  var user: User = _
  val roles = new Roles()

  Injector.get().inject(this)

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
