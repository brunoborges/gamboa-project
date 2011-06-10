package code

import pages.{ PageNotFound, InternalError }
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import code.pages.{ HomePage, SignUp, SignIn }
import org.apache.wicket.Component
import org.apache.wicket.application.IComponentInstantiationListener
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.request.mapper.MountedMapper
import org.apache.wicket.settings.IResourceSettings
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.apache.wicket.util.resource.locator.ResourceStreamLocator
import org.apache.wicket.request.mapper.parameter.PageParametersEncoder

class Application extends AuthenticatedWebApplication {

  override def init() {
    super.init()

    // disable the autoFormMarkupId if you don't want wicket:id to become CSS id
    getComponentInstantiationListeners().add(autoFormMarkupId)

    getComponentInstantiationListeners().add(new SpringComponentInjector(this))
    getMarkupSettings().setStripWicketTags(true)

    mountPage("/404", classOf[PageNotFound])
    mountPage("/500", classOf[InternalError])
  }

  def getHomePage = classOf[HomePage]
  def getSignInPageClass = classOf[SignIn]
  def getWebSessionClass = classOf[Session]

}
