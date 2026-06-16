package code.data

import scala.beans.BeanProperty

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

trait Identifiable extends Serializable {
  @Id @GeneratedValue @BeanProperty var id: String = _
}