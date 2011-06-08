package code.pages

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation

@AuthorizeInstantiation(Array("ADMIN"))
trait TraitAdminPage {

}
