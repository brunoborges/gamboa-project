package code.pages

import org.apache.wicket.RestartResponseException
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink

@AuthorizeInstantiation(Array("USER", "ADMIN"))
class MainPage extends BaseTemplate {

  add(new BookmarkablePageLink("adminLink", classOf[AdminPage]))

}
