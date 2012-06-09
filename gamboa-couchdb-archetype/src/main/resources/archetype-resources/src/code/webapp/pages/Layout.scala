package code.webapp.pages

import org.apache.wicket.markup.html.WebPage
import code.webapp.DSLWicket
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import code.webapp.MySession
import org.apache.wicket.spring.injection.annot.SpringBean

@AuthorizeInstantiation(Array("USER"))
class Layout extends Base {

  val user = MySession.get.user
  homeLink("homeLink")
  
  val nav = container("layoutNavbar")
  nav.logoutLink("logout")

  def saveData() = {
    service.update(user)
    info(getString("DataSaved"))
  }

}
