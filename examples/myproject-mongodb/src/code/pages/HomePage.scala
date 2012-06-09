package code.pages

import org.apache.wicket.RestartResponseException
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import org.apache.wicket.markup.html.WebMarkupContainer;

class HomePage extends BaseTemplate {

  if (session.isSignedIn()) {
    throw new RestartResponseException(classOf[MainPage]);
  }

  setStatelessHint(true);

}
