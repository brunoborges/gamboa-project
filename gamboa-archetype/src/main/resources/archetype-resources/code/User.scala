package code

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

class User(username: String, password: String, enabled: Boolean,
  accountNonExpired: Boolean, credentialsNonExpired: Boolean,
  accountNonLocked: Boolean,
  authorities: Collection[_ <: GrantedAuthority], idUser: String) 
  extends 
   org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired,
  credentialsNonExpired, accountNonLocked, authorities) {

  var id: String = _

  id = idUser

  def getId() = id
  def setId(i: String) = id = i

}
