package code.pages

import org.apache.wicket.request.http.WebResponse
import org.apache.wicket.request.cycle.RequestCycle
import javax.servlet.http.HttpServletResponse;

class PageNotFound extends BaseTemplate {

  override def configureResponse() {
    super.configureResponse()
    RequestCycle.get().getResponse().asInstanceOf[WebResponse].setStatus(HttpServletResponse.SC_NOT_FOUND)
  }

  override def isVersioned() = false

  override def isErrorPage() = true

}
