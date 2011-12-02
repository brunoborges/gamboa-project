package code.data

import scala.reflect.BeanProperty

class User extends Serializable {

  @BeanProperty var id: String = _
  @BeanProperty var name: String = _
  @BeanProperty var email: String = _
  @BeanProperty var password: String = _

}
