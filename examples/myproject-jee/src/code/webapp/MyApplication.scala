package code.webapp

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import code.webapp.pages._
import javax.enterprise.inject.spi.BeanManager
import net.ftlines.wicket.cdi.CdiConfiguration
import net.ftlines.wicket.cdi.ConversationPropagation
import javax.naming.InitialContext
import javax.naming.NamingException

class MyApplication extends AuthenticatedWebApplication {

  override def init() {
    super.init()

    try {
      val manager = new InitialContext().lookup("java:comp/BeanManager").asInstanceOf[BeanManager]

      new CdiConfiguration(manager)
        .setPropagation(ConversationPropagation.NONBOOKMARKABLE)
        .configure(this)
    } catch {
      case e: NamingException ⇒ e.printStackTrace()
    }

    mountPage("/login", classOf[Login])
  }

  def getHomePage = classOf[Index]
  def getSignInPageClass = classOf[Login]
  def getWebSessionClass = classOf[MySession]

}
