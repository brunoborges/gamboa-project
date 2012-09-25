package code.webapp.initializers

import org.apache.wicket.protocol.http.WebApplication
import org.wicketstuff.annotation.scan.AnnotatedMountScanner

trait AnnotatedMountScannerInitializer {
self: WebApplication =>
  
  def initAnnotatedMountScanner() = new AnnotatedMountScanner().scanPackage("code.webapp.pages").mount(this)

}
