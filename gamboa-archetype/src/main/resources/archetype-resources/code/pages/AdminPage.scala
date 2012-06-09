package code.pages

import org.apache.wicket.markup.html.list.PageableListView
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator
import org.apache.wicket.spring.injection.annot.SpringBean
import code.SLink
import org.apache.wicket.markup.html.link.Link
import com.mongodb.casbah.commons.MongoDBObject
import code.services.UserService
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.list.{ ListItem, ListView }
import org.springframework.beans.factory.annotation.Autowired
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import scala.reflect.BeanProperty
import scala.collection.JavaConversions._

@AuthorizeInstantiation(Array("ADMIN"))
class AdminPage extends BaseTemplate {

  @SpringBean
  var service: UserService = _
  val users = service.find()

  object lvUsers extends PageableListView[MongoDBObject]("users", users, 5) {
    override def populateItem(item: ListItem[MongoDBObject]) = {
      val row = item.getModelObject();
      val name = row.getAs[String]("name").get
      val label = new Label("name", name)
      val isAdmin = "ADMIN".equals(row.get("role").get)

      object adminLink extends SLink("adminLink", { () => service.changeRole(name, "ADMIN") }) { setEnabled(!isAdmin) }
      object userLink extends SLink("userLink", { () => service.changeRole(name, "USER") }) { setEnabled(isAdmin) }
      object deleteLink extends SLink("deleteLink", { () => service -= row._id.get.toString() }) { setEnabled(isAdmin) }

      item.add(adminLink, userLink, deleteLink, label)
    }
  }

  add(lvUsers, new PagingNavigator("navigator", lvUsers))
}
