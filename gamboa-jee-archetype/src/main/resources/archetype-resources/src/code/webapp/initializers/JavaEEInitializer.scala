package code.webapp.initializers

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication

import javax.enterprise.inject.spi.BeanManager
import javax.naming.InitialContext
import net.ftlines.wicket.cdi.CdiConfiguration
import net.ftlines.wicket.cdi.ConversationPropagation

trait JavaEEInitializer {
self: AuthenticatedWebApplication =>

  def initJEEIntegration(propagation: ConversationPropagation = ConversationPropagation.NONBOOKMARKABLE) = {
    val manager = new InitialContext().lookup("java:comp/BeanManager").asInstanceOf[BeanManager]
    new CdiConfiguration(manager).setPropagation(propagation).configure(this)
  }

}