package code.pages

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.http.WebResponse
import org.apache.wicket.request.cycle.RequestCycle
import javax.servlet.http.HttpServletResponse;

class PageNotFound extends ErrorPage(HttpServletResponse.SC_NOT_FOUND) {

}
