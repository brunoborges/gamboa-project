package code

import org.apache.wicket.protocol.http.WebApplication
import code.pages._
import org.apache.wicket.spring.injection.annot.SpringComponentInjector

class Application extends WebApplication {

  getComponentInstantiationListeners().add(new SpringComponentInjector(this))
  getMarkupSettings().setStripWicketTags(true)

  def getHomePage = classOf[Index]

}
