package code.webapp.pages

import code.data.User
import code.webapp.Session
import org.apache.wicket.markup.html.form.{ RequiredTextField, PasswordTextField, Form, Button }
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.validation.validator.EmailAddressValidator
import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.validation.IValidatable
import org.apache.wicket.Session
import org.wicketstuff.annotation.mount.MountPath

@MountPath("login")
class Login extends Layout {
  val loginForm = new FullLoginForm("form")
  add(loginForm)
  feedback("feedbackPanel")
}
