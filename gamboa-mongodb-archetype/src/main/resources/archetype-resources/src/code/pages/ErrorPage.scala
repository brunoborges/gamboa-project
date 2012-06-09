package code.pages

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.http.WebResponse
import org.apache.wicket.request.cycle.RequestCycle
import javax.servlet.http.HttpServletResponse;

abstract class ErrorPage(status: Integer) extends WebPage {

  override def configureResponse() {
    super.configureResponse()
    val response = RequestCycle.get().getResponse().asInstanceOf[WebResponse]
    response.setStatus(status)
  }

  override def isVersioned() = false
  override def isErrorPage() = true

}
