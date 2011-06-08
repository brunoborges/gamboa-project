package code.pages

import org.apache.wicket.model.StringResourceModel
import code.Record
import code.services.ContactService
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.{ TextField, TextArea, RequiredTextField, Form }
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.validation.validator.EmailAddressValidator

class Contact extends BaseTemplate {

  @SpringBean
  var service: ContactService = _

  object contactForm extends Form[Record]("contactForm") {
    setOutputMarkupId(true);
    setOutputMarkupPlaceholderTag(true)

    add(new FeedbackPanel("formFeedback"))

    setModel(new CompoundPropertyModel[Record](new Record()));
    add(new TextField[String]("name"));
    add(new RequiredTextField[String]("subject"));

    object emailField extends RequiredTextField[String]("email") {
    	add(EmailAddressValidator.getInstance())
    }
    add(emailField);

    object textArea extends TextArea[String]("message") { setRequired(true) }
    add(textArea);

    override def onSubmit() {
      if (!hasError()) {
        val contato = getModelObject()
        service.send(contato)
        setModelObject(new Record())
        info(getString("contact.sent"))
      }
    }
  }

  add(contactForm)

}

