package code.webapp

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import code.webapp.pages.{ Login, Index }

class MyApplication extends AuthenticatedWebApplication {

  override def init() {
    super.init()

    getComponentInstantiationListeners().add(new SpringComponentInjector(this))

    mountPage("/login", classOf[Login])
  }

  def getHomePage = classOf[Index]
  def getSignInPageClass = classOf[Login]
  def getWebSessionClass = classOf[MySession]

}
