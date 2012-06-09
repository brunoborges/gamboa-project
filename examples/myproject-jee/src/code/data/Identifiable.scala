package code.data

import scala.reflect.BeanProperty
import javax.persistence.{ Id, GeneratedValue }

trait Identifiable extends Serializable {

  @Id
  @GeneratedValue
  @BeanProperty
  var id: String = _

}