package code

import scala.collection.JavaConversions._
import org.apache.wicket.datetime.markup.html.form.DateTextField
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{ TextField, TextArea, RadioGroup, Radio, PasswordTextField, DropDownChoice, Button }
import org.apache.wicket.markup.html.link.{ Link, BookmarkablePageLink }
import org.apache.wicket.markup.html.link.ExternalLink
import org.apache.wicket.markup.html.list.{ ListView, ListItem }
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.model.IModel
import org.apache.wicket.model.{ PropertyModel, Model, LoadableDetachableModel, CompoundPropertyModel }
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.MarkupContainer
import org.apache.wicket.Component
import org.apache.wicket.Page
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.markup.html.list.PageableListView
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator
import org.apache.wicket.markup.html.navigation.paging.IPageable
import org.apache.wicket.markup.html.basic.MultiLineLabel

trait DSLWicket {
  self: MarkupContainer ⇒

  def container(id: String): DSLMarkupContainer = { val c = new WebMarkupContainer(id) with DSLMarkupContainer; add(c); c }
  def hide() = setVisibilityAllowed(false)
  def show() = setVisibilityAllowed(true)
  def hide(c: Component*) = c.foreach((x) ⇒ x.setVisibilityAllowed(false))
  def show(c: Component*) = c.foreach((x) ⇒ x.setVisibilityAllowed(true))
  def feedback(id: String = "feedback") = add(new FeedbackPanel(id))
  def ldm[T](loadF: () ⇒ T): IModel[T] = {
    val ldm = new LoadableDetachableModel[T] { override def load(): T = loadF() }; ldm
  }
  // MultiLineLabel
  def multiLineLabel[T](id: String, model: IModel[T] = null): MultiLineLabel = { val label = new MultiLineLabel(id, model); add(label); label }
  def multiLineLabel[T](id: String, value: String): MultiLineLabel = { val label = new MultiLineLabel(id, value); add(label); label }
  // Label
  def label[T](id: String, model: IModel[T] = null): Label = { val label = new Label(id, model); add(label); label }
  def label[T](id: String, value: String): Label = { val label = new Label(id, value); add(label); label }
  implicit def ser2model[S <: Serializable](ser: S): IModel[S] = Model.of(ser)
  def textField[T](id: String)(implicit m: scala.reflect.Manifest[T]): RequireableTextField[T] = {
    val field = new TextField[T](id) with RequireableTextField[T]; field.setType(m.erasure); add(field); field
  }
  def emailField(id: String): RequireableTextField[String] = {
    val ef = textField[String](id)
    ef.add(RfcCompliantEmailAddressValidator.getInstance()); ef
  }
  def passField(id: String): PasswordTextField = {
    val pass = new PasswordTextField(id); add(pass); pass;
  }
  def textArea[T](id: String)(implicit m: scala.reflect.Manifest[T]): RequireableTextArea[T] = {
    val field = new TextArea[T](id) with RequireableTextArea[T]; field.setType(m.erasure); add(field); field
  }
  def dateField(id: String, format: String = "dd/MM/yyyy"): DateTextField = {
    val field = DateTextField.forDatePattern(id, format); add(field); field
  }
  def button(id: String, submit: () ⇒ _): Button = {
    val button = new Button(id) { override def onSubmit() = submit() }
    add(button); button
  }
  def cancelButton(id: String, submit: () ⇒ _): Button = {
    val button = new Button(id) { override def onSubmit() = submit() }
    button.setDefaultFormProcessing(false)
    add(button); button
  }
  def cancelButton(id: String): Button = {
    val button = new Button(id).setDefaultFormProcessing(false)
    add(button); button
  }
  def button(id: String, submit: (Button) ⇒ _): Button = {
    val button = new Button(id) { override def onSubmit() = submit(this) }
    add(button); button
  }
  def pmodel[T](obj: T, expression: String): IModel[T] = { new PropertyModel[T](obj, expression) }
  def cmodel[T](obj: T): IModel[T] = { new CompoundPropertyModel[T](obj); }
  def compound[T](obj: T): IModel[T] = { val m = new CompoundPropertyModel[T](obj); setDefaultModel(m); m }
  def blink(id: String, clazz: Class[_ <: Page], params: PageParameters = null): Link[AnyRef] = {
    val b = new BookmarkablePageLink[AnyRef](id, clazz, params); add(b); b
  }
  def link[T](id: String, click: () ⇒ _): SLink[T] = {
    val l = new Link[T](id) with SLink[T] { override def onClick() = click() }
    add(l); l
  }
  def link[T](id: String, click: (SLink[_]) ⇒ _): SLink[T] = {
    val l = new Link[T](id) with SLink[T] { override def onClick() = click(this) }
    add(l); l
  }
  def link[T](container: WebMarkupContainer, id: String, click: (SLink[_]) ⇒ _): SLink[T] = {
    val l = new Link[T](id) with SLink[T] { override def onClick() = click(this) }
    container.add(l); l
  }
  def listView[T](id: String, populate: (ListItem[T]) ⇒ _): ListView[T] = {
    val lv = new ListView[T](id) { override def populateItem(item: ListItem[T]) = populate(item) }
    add(lv); lv
  }
  def listView[T](id: String, populate: (ListItem[T]) ⇒ _, m: IModel[_ <: java.util.List[_ <: T]]): ListView[T] = {
    val lv = new ListView[T](id, m) { override def populateItem(item: ListItem[T]) = populate(item) }
    add(lv); lv
  }
  def pageableListView[T](id: String, populate: (ListItem[T]) ⇒ _, m: IModel[_ <: java.util.List[_ <: T]], pageSize: Int): DSLPageable[T] = {
    val lv = new PageableListView[T](id, m, pageSize) with DSLPageable[T] { override def populateItem(item: ListItem[T]) = populate(item) }
    add(lv); lv
  }
  def pageableListView[T](id: String, populate: (ListItem[T]) ⇒ _, l: java.util.List[_ <: T], pageSize: Int): DSLPageable[T] = {
    val lv = new PageableListView[T](id, l, pageSize) with DSLPageable[T] {
      override def populateItem(item: ListItem[T]) = populate(item)
    }
    add(lv); lv
  }
  def pagingNavigator(id: String, pageable: IPageable): PagingNavigator = new PagingNavigator(id, pageable)
  def mobject[T](obj: T) = setDefaultModelObject(obj)
  def mobject[T]() = getDefaultModelObject().asInstanceOf[T]
  def dropDownChoice[T](id: String, choices: java.util.List[_ <: T] = null)(implicit m: scala.reflect.Manifest[T]): DropDownChoice[T] = {
    val dropdown = new DropDownChoice[T](id, choices); add(dropdown); dropdown
  }
  def emailLink(id: String, email: String, label: String) { val el = new ExternalLink(id, "mailto:" + email, label); add(el); el; }
  def emailLink(id: String, email: String) { emailLink(id, email, email) }
  def select[T](id: String) = {
    val ddc = new DropDownChoice[T](id)
    add(ddc); ddc
  }
  def select[T](id: String, elements: List[T]) = {
    val ddc = new DropDownChoice[T](id, elements)
    add(ddc); ddc
  }
  def radioGroup[T](id: String, model: IModel[T] = null): DSLRadioGroup[T] = {
    val group = new RadioGroup[T](id) with DSLRadioGroup[T]
    add(group); group
  }
  trait DSLRadioGroup[T] extends RadioGroup[T] {
    def radio[T](id: String, m: IModel[T] = null): Radio[T] = {
      val radio = new Radio[T](id, m, this.asInstanceOf[RadioGroup[T]]); add(radio); radio
    }
    def radio(id: String, value: String): DSLRadioGroup[T] = { radio[String](id, Model.of(value)); this }
  }
  trait SLink[T] extends Link[T] with DSLWicket
  trait RequireableFormComponent[T] extends FormComponent[T] with DSLWicket { def required() = setRequired(true) }
  trait RequireableTextField[T] extends TextField[T] with RequireableFormComponent[T]
  trait RequireableTextArea[T] extends TextArea[T] with RequireableFormComponent[T]
  trait DSLMarkupContainer extends WebMarkupContainer with DSLWicket
  trait DSLPageable[T] extends PageableListView[T] {
    def navigator(id: String) = this.getParent().add(new PagingNavigator(id, this))
  }
  object OddBehavior extends AttributeAppender("class", Model.of("odd"))

}
