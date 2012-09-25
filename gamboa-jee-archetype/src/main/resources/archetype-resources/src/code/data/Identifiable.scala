package code.data

import scala.reflect.BeanProperty

import javax.persistence.GeneratedValue
import javax.persistence.Id

trait Identifiable extends Serializable {
  @Id @GeneratedValue @BeanProperty var id: String = _
}