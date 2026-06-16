package code.services.email

import java.io.StringWriter

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import org.springframework.stereotype.Service

import scala.jdk.CollectionConverters._

trait EmailTemplateEngine {
  def transform(template: String, values: Map[String, Object]): String
}

@Service
class EmailTemplateEngineImpl extends EmailTemplateEngine {

  private val velocity = new VelocityEngine()
  velocity.setProperty(RuntimeConstants.RESOURCE_LOADERS, "class")
  velocity.setProperty("resource.loader.class.class", classOf[ClasspathResourceLoader].getName)
  velocity.init()

  def transform(template: String, values: Map[String, Object]): String = {
    val writer = new StringWriter()
    velocity.mergeTemplate(template, "UTF-8", new VelocityContext(values.asJava), writer)
    writer.toString
  }

}
