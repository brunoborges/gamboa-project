package code.webapp.pages

import code.data.User
import code.services.UserService
import code.webapp.MySession
import org.apache.wicket.markup.html.form.{ RequiredTextField, PasswordTextField, Form, Button }
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.validation.validator.EmailAddressValidator
import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.validation.IValidatable
import org.apache.wicket.Session

class Login extends Base {

  val gbuser = new User()

  val f: SForm[User] = form[User]("form")

  f.scmodel(gbuser)
  f.feedback("feedback")
  val emailField = f.emailField("email")
  emailField.required()
  f.passField("password")

  val btnLogin = f.button("login", (btn: Button) ⇒ {
    val email = gbuser.getEmail()
    val pass = gbuser.getPassword()
    if (MySession.get.authenticate(email, pass)) {
    	setResponsePage(classOf[Index])
    } else {
      error(getString("WrongUserPass"))
    }
  })
  f.setDefaultButton(btnLogin)

  f.button("create", (btn: Button) ⇒ {
    val userExists = service.exists(gbuser.getEmail())

    if (userExists) {
      error(getString("EmailExists"))
    } else {
      val gbuser = f.mobject[User]()
      service.create(gbuser)
      info(getString("ThanksRegistration"))
    }
  })

  // forgot password
  f.submitLink("forgotpass", (sl: SubmitLink) ⇒ {
    emailField.validate()
    if (!emailField.hasErrorMessage()) {
      service.rememberPassword(gbuser.getEmail())
      info(getString("NewPassSent"))
    }
  }).setDefaultFormProcessing(false)

}
