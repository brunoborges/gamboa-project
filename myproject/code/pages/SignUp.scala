package code.pages

import code.Record
import code.services.SignUpService
import org.apache.wicket.markup.html.form.{ RequiredTextField, PasswordTextField, Form }
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.validation.{ IValidator, IValidatable, ValidationError }
import org.apache.wicket.validation.validator.{ EmailAddressValidator, StringValidator }
import org.apache.wicket.validation.validator.StringValidator.MinimumLengthValidator

class SignUp extends BaseTemplate {

  @SpringBean
  var signUp: SignUpService = _

  add(new FormSignUp("formSignUp"))

  class FormSignUp(id: String) extends Form[Record](id) {
    setModel(new CompoundPropertyModel[Record](new Record()))

    val em = new RequiredTextField[String]("email")
    val pass = new PasswordTextField("password")
    val name = new RequiredTextField[String]("name")

    em.add(EmailAddressValidator.getInstance());
    em.add(new IValidator[String]() {
      def validate(validatable: IValidatable[String]) {
        val email = validatable.getValue();

        if (signUp.emailExists(email)) {
          val error = new ValidationError();
          error.addMessageKey("EmailExists");
          error.setVariable("email", email);
          validatable.error(error);
        }
      }
    });
    pass.add(new MinimumLengthValidator(8));

    add(em)
    add(pass)
    add(name)

    add(new FeedbackPanel("feedbackPanel"))

    override def onSubmit() = {
      val signUpVO = getModelObject()
      signUp.signUp(signUpVO)
      setResponsePage(classOf[SignUpSuccess])
    }
  }
}
