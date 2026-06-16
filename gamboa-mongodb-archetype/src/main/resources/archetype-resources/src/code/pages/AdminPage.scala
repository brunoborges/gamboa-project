package code.pages

import code.SLink
import code.common.Record
import code.services.UserService
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.list.{ ListItem, PageableListView }
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import scala.jdk.CollectionConverters._

@AuthorizeInstantiation(Array("ADMIN"))
class AdminPage extends BaseTemplate {

  @SpringBean
  var service: UserService = _

  val users: java.util.List[Record] = service.find().asJava

  val lvUsers = new PageableListView[Record]("users", users, 5L) {
    override def populateItem(item: ListItem[Record]): Unit = {
      val row = item.getModelObject()
      val name = row.get[String]("name")
      val isAdmin = "ADMIN" == row.get[String]("role")
      val label = new Label("name", name)

      val adminLink = new SLink("adminLink", { service.changeRole(name, "ADMIN") })
      adminLink.setEnabled(!isAdmin)
      val userLink = new SLink("userLink", { service.changeRole(name, "USER") })
      userLink.setEnabled(isAdmin)
      val deleteLink = new SLink("deleteLink", { service -= row.id })
      deleteLink.setEnabled(isAdmin)

      item.add(adminLink, userLink, deleteLink, label)
    }
  }

  add(lvUsers, new PagingNavigator("navigator", lvUsers))
}
