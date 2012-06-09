package code

/**
 * A collection of simple components, most of which simply allow you to use Fodel's
 * directly, or pass in anonymous functions for their onSubmit methods etc.
 *
 * @author Antony Stubbs
 */

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{ Form, TextField }
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.list.{ ListView, ListItem, PropertyListView }
import org.apache.wicket.model.{ IModel, Model }

class SForm[T](id: String, model: IModel[T], onSubmitFunc: => Unit) extends Form[T](id) {
  override def onSubmit = onSubmitFunc
}

class SLink(id: String, onClickFunc: => Unit) extends Link(id) {
  override def onClick = onClickFunc
}

class SListView[T](id: String, list: java.util.List[T], populateItemFunc: (ListItem[T]) => Unit) extends ListView[T](id, list) {
  override def populateItem(li: ListItem[T]) = populateItemFunc(li)
}
