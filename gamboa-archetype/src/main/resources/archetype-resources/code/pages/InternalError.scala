package code.pages

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.http.WebResponse
import org.apache.wicket.request.cycle.RequestCycle
import javax.servlet.http.HttpServletResponse;

class InternalError extends ErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {

}
