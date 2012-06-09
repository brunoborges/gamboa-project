package code.data

import scala.reflect.BeanProperty
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.ElementCollection
import javax.persistence.OneToMany
import javax.persistence.CascadeType
import javax.persistence.FetchType

@Entity
@Table(name="USERS")
class User extends Identifiable {

  @BeanProperty
  var name: String = _

  @BeanProperty
  var email: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var disableAll: Boolean = _

}
