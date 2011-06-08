package code.services.email;

import java.util.Map;

trait EmailTemplateEngine {

  def transform(template: String, values: Map[String, Object]): String

}

