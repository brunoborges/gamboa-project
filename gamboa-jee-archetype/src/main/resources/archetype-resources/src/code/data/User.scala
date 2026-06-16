package code.data

import scala.beans.BeanProperty
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "USERS")
class User extends Identifiable {
  @BeanProperty var email: String = _
  @BeanProperty var password: String = _
}
