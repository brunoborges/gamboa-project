package code.webapp.pages

import code.webapp.Session
import org.wicketstuff.scala.Fodel

abstract class Layout extends Base {
  
  val menuLoggedIn = container("menuLoggedIn")
  val navbarLoginForm = new LoginForm("loginForm")

  add(navbarLoginForm)
  homeLink("homeLink")

  menuLoggedIn.logoutLink("logoutLink")
  menuLoggedIn.label("labelUsername", new Fodel(Session.get.user.email))

  override def onConfigure() = {
    menuLoggedIn.setVisible(Session.get.isSignedIn())
    navbarLoginForm.setVisible(!Session.get.isSignedIn())
  }

}
