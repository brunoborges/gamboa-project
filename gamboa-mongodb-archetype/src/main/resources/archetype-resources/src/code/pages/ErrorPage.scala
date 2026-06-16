package code.pages

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.http.WebResponse

abstract class ErrorPage(status: Integer) extends WebPage {

  override def configureResponse(response: WebResponse): Unit = {
    super.configureResponse(response)
    response.setStatus(status)
  }

  override def isVersioned() = false
  override def isErrorPage() = true

}
