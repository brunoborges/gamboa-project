package code.services.email;

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import scala.reflect.BeanProperty
import scala.collection.JavaConversions._
import javax.ejb.Stateless
import javax.inject.Inject
import java.io.StringWriter

trait EmailTemplateEngine {
  def transform(template: String, model: Map[String, Object]): String
}

@Stateless
class EmailTemplateEngineImpl extends EmailTemplateEngine {

  @Inject var velocity: VelocityEngine = _

  def transform(template: String, model: Map[String, Object]): String = {
    val writer = new StringWriter()
    val velocityContext = new VelocityContext(model)
    velocity.mergeTemplate(template, "UTF-8", velocityContext, writer)
    writer.toString()
  }

}