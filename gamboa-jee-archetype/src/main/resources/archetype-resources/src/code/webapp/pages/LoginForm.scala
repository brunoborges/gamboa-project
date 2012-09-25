package code.webapp.pages

import code.webapp.Session
import code.services.UserService
import code.data.User

import javax.inject.Inject

import org.wicketstuff.scala.DSLWicket
import org.apache.wicket.markup.html.form.{Button,SubmitLink,Form}
import org.apache.wicket.Component

class LoginForm(id: String, full: Boolean = false)
  extends Form[User](id) with DSLWicket {

  @Inject var usrService: UserService = _

  val emailField: STextField[String] = emailField("email")
  val gbuser = new User()
  
  emailField.required()
  passField("password")
  scmodel(gbuser)

  val loginButton = button("login", (btn: Button) ⇒ {
    Session.get.signIn(gbuser.email, gbuser.password) match {
      case true => setResponsePage(classOf[Index])
      case false => {
        if (full == false) {
          var loginPage = new Login()
          loginPage.loginForm.mobject(gbuser)
          loginPage.loginForm.error(getString("WrongUserPass"))
          setResponsePage(loginPage)
        } else {
          error(getString("WrongUserPass"))
        }
      }
    }
  })

  setDefaultButton(loginButton)
}

class FullLoginForm(id: String) extends LoginForm(id, true) {

  button("create", (btn: Button) ⇒ {
    usrService.exists(gbuser.getEmail()) match {
      case true => error(getString("EmailExists"))
      case false =>
        val gbuser = mobject[User]()
        usrService.create(gbuser)
        info(getString("ThanksRegistration"))
    }
  })

  // forgot password
  submitLink("forgotpass", (sl: SubmitLink) ⇒ {
    emailField.validate()
    if (!emailField.hasErrorMessage()) {
      usrService.rememberPassword(gbuser.getEmail())
      info(getString("NewPassSent"))
    }
  }).setDefaultFormProcessing(false)

}