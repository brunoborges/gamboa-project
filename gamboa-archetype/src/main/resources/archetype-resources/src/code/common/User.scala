package code

import java.util.Collection

import scala.reflect.BeanProperty
import org.springframework.security.core.GrantedAuthority

class User(username: String, 
           password: String, 
           enabled: Boolean,
           accountNonExpired: Boolean, 
           credentialsNonExpired: Boolean,
           accountNonLocked: Boolean,
           authorities: Collection[_ <: GrantedAuthority], idUser: String) 
  extends 
   org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired,
  credentialsNonExpired, accountNonLocked, authorities) {

  @BeanProperty
  var id: String = _

  @BeanProperty 
  var name: String = _

  @BeanProperty
  var email: String = _

  id = idUser

}
