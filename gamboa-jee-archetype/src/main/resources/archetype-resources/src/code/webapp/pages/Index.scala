package code.webapp.pages

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation

@AuthorizeInstantiation(Array("USER"))
class Index extends Layout {

  // uncomment if you want to access the Service
  // @Inject var service: Service = _

}
