package code.pages

import org.apache.wicket.RestartResponseException
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink


class MainPage extends BaseTemplate with TraitUserPage {

  add(new BookmarkablePageLink("adminLink", classOf[AdminPage]))

}
