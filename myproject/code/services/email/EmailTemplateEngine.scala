package code.services.email;

import org.apache.velocity.app.VelocityEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.ui.velocity.VelocityEngineUtils
import scala.reflect.BeanProperty
import scala.collection.JavaConversions._

trait EmailTemplateEngine {
  def transform(template: String, values: Map[String, Object]): String
}

@Service
class EmailTemplateEngineImpl extends EmailTemplateEngine {

  @Autowired
  @BeanProperty
  var velocity: VelocityEngine = _

  def transform(template: String, values: Map[String, Object]): String = {
    VelocityEngineUtils.mergeTemplateIntoString(velocity, template, values)
  }

}
