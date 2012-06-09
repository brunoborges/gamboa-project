package code.services.email;

import org.apache.velocity.app.VelocityEngine
import scala.reflect.BeanProperty
import scala.collection.JavaConversions._
import javax.ejb.Stateless
import javax.inject.Inject

trait EmailTemplateEngine {
  def transform(template: String, values: Map[String, Object]): String
}

@Stateless
class EmailTemplateEngineImpl extends EmailTemplateEngine {

  //@Inject var velocity: VelocityEngine = _

  def transform(template: String, values: Map[String, Object]): String = {
    //VelocityEngineUtils.mergeTemplateIntoString(velocity, template, values)
    // TODO
    ""
  }

}
