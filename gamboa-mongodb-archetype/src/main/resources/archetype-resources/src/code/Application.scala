package code

import pages._
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import org.apache.wicket.Component
import org.apache.wicket.application.IComponentInstantiationListener
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.spring.injection.annot.SpringComponentInjector

class Application extends AuthenticatedWebApplication {

  override def init(): Unit = {
    super.init()

    // enable the autoFormMarkupId if you want wicket:id to become CSS id
    // getComponentInstantiationListeners().add(autoFormMarkupId)

    getComponentInstantiationListeners().add(new SpringComponentInjector(this))
    getMarkupSettings().setStripWicketTags(true)

    mountPage("/404", classOf[PageNotFound])
    mountPage("/500", classOf[InternalError])
  }

  def getHomePage = classOf[HomePage]
  def getSignInPageClass = classOf[SignIn]
  def getWebSessionClass = classOf[Session]

}
