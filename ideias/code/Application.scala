package code

import org.apache.wicket.protocol.http.WebApplication
import code.pages.Index

class Application extends WebApplication {

  def getHomePage = classOf[Index]

}
