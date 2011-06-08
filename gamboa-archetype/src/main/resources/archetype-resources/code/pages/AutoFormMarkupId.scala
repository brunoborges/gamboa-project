package code

import org.apache.wicket.Component
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.application.IComponentInstantiationListener

object autoFormMarkupId extends IComponentInstantiationListener {
  def onInstantiation(component: Component) {
    if (component.isInstanceOf[FormComponent[_]]) {
      component.setOutputMarkupId(true)
      component.setMarkupId(component.getId())
    }
  }
}
