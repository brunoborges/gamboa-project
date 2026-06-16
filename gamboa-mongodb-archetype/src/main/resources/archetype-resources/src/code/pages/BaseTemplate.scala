package code.pages

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import code.Session
import org.apache.wicket.markup.html.{ WebPage, WebMarkupContainer }
import org.apache.wicket.markup.html.link.{ Link, BookmarkablePageLink }

class BaseTemplate extends WebPage {

  class BookmarkLink(id: String, clazz: Class[_ <: BaseTemplate], block: () => Boolean) extends WebMarkupContainer(id) {
    def this(id: String, clazz: Class[_ <: BaseTemplate]) = this(id, clazz, { () => true })
    add(new BookmarkablePageLink[BaseTemplate](id + "Link", clazz))
    override def isVisible = block()
  }

  class HardLink(id: String, link: Link[_], block: () => Boolean) extends WebMarkupContainer(id) {
    def this(id: String, link: Link[_]) = this(id, link, { () => true })
    add(link)
    override def isVisible = block()
  }

  object logOutLink extends Link[Any]("logOutLink") {
    override def getStatelessHint = true
    override def onClick(): Unit = {
      session.signOut()
      setResponsePage(classOf[HomePage])
    }
  }

  object signInLink extends Link[Any]("signInLink") {
    override def getStatelessHint = true
    override def onClick(): Unit = setResponsePage(classOf[SignIn])
  }

  def session: Session = AuthenticatedWebSession.get().asInstanceOf[Session]

  val notSignedIn = () => !session.isSignedIn()
  val signedIn = () => session.isSignedIn()

  add(new BookmarkLink("home", classOf[HomePage]))
  add(new BookmarkLink("signUp", classOf[SignUp], notSignedIn))
  // add(new BookmarkLink("profile", classOf[Profile], signedIn))
  add(new BookmarkLink("contact", classOf[Contact]))
  add(new HardLink("logOut", logOutLink, signedIn))
  add(new HardLink("signIn", signInLink, notSignedIn))

}


