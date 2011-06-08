package code.pages

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.list.ListItem
import code.services.SignUpService
import org.springframework.beans.factory.annotation.Autowired
import code.Record
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import scala.collection.JavaConversions._

class AdminPage extends BaseTemplate with TraitAdminPage {

  @Autowired
  var service: SignUpService = _
  
  val users: List[Record] = service.listUsers()

  object lvUsers extends ListView[Record]("users", users) {
    override def populateItem(item: ListItem[Record]) = {
      val record = item.getModelObject();
      val isAdmin = "true".equals(record.<("isAdmin"))

      item.add(new Label("name", record.<("name").toString()))
      item.add(new Label("name", record.<("name").toString()))
    }
  }
}
