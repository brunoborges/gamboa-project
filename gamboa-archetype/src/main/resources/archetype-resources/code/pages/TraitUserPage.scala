package code.pages

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation

@AuthorizeInstantiation(Array("USER"))
trait TraitUserPage {

}
