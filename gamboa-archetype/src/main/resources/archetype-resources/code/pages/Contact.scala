package code.pages

import code.services.ContactService
import java.util.HashMap
import org.apache.wicket.model.StringResourceModel
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.{ TextField, TextArea, RequiredTextField, Form }
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.validation.validator.EmailAddressValidator
import scala.collection.JavaConversions._

class Contact extends BaseTemplate {

  @SpringBean
  var service: ContactService = _

  object contactForm extends Form[HashMap[String, String]]("contactForm") {
    setOutputMarkupId(true);
    setOutputMarkupPlaceholderTag(true)

    add(new FeedbackPanel("formFeedback"))

    setModel(new CompoundPropertyModel[HashMap[String, String]](new HashMap[String, String]()));
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
        service.send(contato.toMap)
        contato.clear()
        info(getString("contact.sent"))
      }
    }
  }

  add(contactForm)

}

