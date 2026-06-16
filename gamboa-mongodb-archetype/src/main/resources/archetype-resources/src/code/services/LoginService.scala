package code.services

import code.User
import code.common.Record
import java.util.{ ArrayList, Collection }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.{ UserDetailsService, UsernameNotFoundException }
import org.springframework.stereotype.Service

@Service
class LoginService extends UserDetailsService {

  @Autowired
  var users: UserService = _

  def loadUserByUsername(email: String): User =
    users.find(email) match {
      case Some(record) => buildUser(record)
      case None         => throw new UsernameNotFoundException(email)
    }

  private def buildUser(user: Record): User = {
    val authorities: Collection[_ <: GrantedAuthority] = loadAuthorities(user)
    val username = user.get[String]("email")
    val password = user.get[String]("password")

    new User(username, password, true, true, true, true, authorities, user.id)
  }

  private def loadAuthorities(user: Record): Collection[_ <: GrantedAuthority] = {
    val roles = new ArrayList[GrantedAuthority]()
    user.getOpt[String]("role").foreach(role => roles.add(new SimpleGrantedAuthority(role)))
    roles
  }

}
