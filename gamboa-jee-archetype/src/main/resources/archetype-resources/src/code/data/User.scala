package code.data

import scala.reflect.BeanProperty
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "USERS")
class User extends Identifiable {
  @BeanProperty var email: String = _
  @BeanProperty var password: String = _
}
