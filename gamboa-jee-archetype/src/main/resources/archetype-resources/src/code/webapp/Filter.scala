package code.webapp

import javax.servlet.annotation.{ WebFilter, WebInitParam }
import org.apache.wicket.protocol.http.WicketFilter

@WebFilter(value = Array("/*"), initParams = Array(
	new WebInitParam(name = "applicationClassName", value = "code.webapp.Application"),
	new WebInitParam(name = "filterMappingUrlPattern", value = "/*")
))
class Filter extends WicketFilter