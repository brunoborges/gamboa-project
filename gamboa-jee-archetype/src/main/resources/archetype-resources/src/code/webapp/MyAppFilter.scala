package code.webapp

import javax.servlet.annotation.{ WebFilter, WebInitParam }
import org.apache.wicket.protocol.http.WicketFilter

@WebFilter(value = Array("/*"), initParams = Array(
	new WebInitParam(name = "applicationClassName", value = "code.webapp.MyApplication"),
	new WebInitParam(name = "filterMappingUrlPattern", value = "/*")
))
class MyAppFilter extends WicketFilter