package code.services.email;

import java.io.{File, FileReader, StringWriter}

import scala.collection.JavaConversions.mapAsJavaMap

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine

import javax.ejb.Singleton

trait EmailTemplateEngine {
  def transform(template: String, model: Map[String, Object]): String
}

@Singleton
class EmailTemplateEngineImpl extends EmailTemplateEngine {

  val velocity: VelocityEngine = new VelocityEngine()

  def transform(template: String, model: Map[String, Object]): String = {
    var resource = getClass().getClassLoader().getResource(template)
    val writer = new StringWriter()
    val velocityContext = new VelocityContext(model)

    val file = new File(resource.toURI())
    val reader = new FileReader(file)
    velocity.evaluate(velocityContext, writer, file.getName(), reader)
    System.out.println(writer)
    writer.toString()
  }

}