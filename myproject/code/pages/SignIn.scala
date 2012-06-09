package code.pages

import org.apache.wicket.validation.validator.EmailAddressValidator
import org.apache.wicket.extensions.yui.calendar.DatePicker
import org.apache.wicket.datetime.markup.html.form.DateTextField
import java.util.Date
import org.apache.wicket.model.Model
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.markup.html.form.Form

class SignIn extends BaseTemplate {

  add(new SignInForm("signInForm"))

  class SignInForm(id: String) extends Form[SignInForm](id) {

    var email: String = _
    var password: String = _

    setModel(new CompoundPropertyModel[SignInForm](this))
    add(new RequiredTextField[String]("email").add(EmailAddressValidator.getInstance()));
    add(new PasswordTextField("password"));

    override def onSubmit() = {
      if (session.signIn(email, password)) {
        setDefaultResponsePageIfNecessary();
      } else {
        error(getString("login.failed"));
      }
    }

    def setDefaultResponsePageIfNecessary() = if (!continueToOriginalDestination()) setResponsePage(classOf[HomePage])

  }
}
