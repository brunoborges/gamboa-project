package code.services

import code.{ Record, User }
import java.util.{ Collection, ArrayList }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class LoginMongoDBService extends UserDetailsService {

  @Autowired
  var signUp: SignUpService = _

  def loadUserByUsername(username: String): User = {
    val user = signUp.loadUser(username);
    val authorities: Collection[_ <: GrantedAuthority] = loadAuthorities(user);
    val accountNonLocked = true;
    val credentialsNonExpired = true;
    val password = user.get("password").toString();
    val enabled = true;
    val accountNonExpired = true;

    new User(username, password, enabled,
      accountNonExpired, credentialsNonExpired, accountNonLocked,
      authorities, user.get("_id").toString())
  }

  private def loadAuthorities(user: Record): Collection[_ <: GrantedAuthority] = {
    val roles = new ArrayList[GrantedAuthority]()
    roles.add(new GrantedAuthorityImpl("USER"));
    return roles;
  }

}
