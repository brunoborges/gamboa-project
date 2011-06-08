package code.services.email;

import org.apache.velocity.app.VelocityEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.ui.velocity.VelocityEngineUtils
import scala.reflect.BeanProperty

@Service
class EmailTemplateEngineImpl extends EmailTemplateEngine {

  @Autowired
  var velocity: VelocityEngine = _

  def transform(template: String, values: java.util.Map[String, Object]): String = {
    VelocityEngineUtils.mergeTemplateIntoString(velocity, template, values)
  }

}
