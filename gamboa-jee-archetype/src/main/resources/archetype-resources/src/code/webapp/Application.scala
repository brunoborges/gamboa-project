package code.webapp

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import code.webapp.pages._
import code.webapp.initializers._

/*
 * If you don't want a secure web application, replace AuthenticatedWebApplication with WebApplication
 */
class Application extends AuthenticatedWebApplication
  with JavaEEInitializer
  with AnnotatedMountScannerInitializer {

  def getHomePage = classOf[Index]

  // if you are using simple WebApplication, you may comment these two methods
  def getSignInPageClass = classOf[Login]
  def getWebSessionClass = classOf[Session]

  override def init() {
    super.init()

    initJEEIntegration() // from JavaEEInitializer
    initAnnotatedMountScanner() // from AnnotatedMOuntScannerInitializer
  }
  
}