package code.services

import code.User
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.Imports._
import java.util.{ Collection, ArrayList }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import scala.reflect.BeanProperty

@Service
class LoginMongoDBService extends UserDetailsService {

  @Autowired
  var signUp: SignUpService = _

  @Autowired
  var users: UserService = _

  def loadUserByUsername(email: String): User = {
    users.find(email) match {
      case Some(value) => buildUser(value)
      case None => null
    }
  }

  private def buildUser(user: MongoDBObject): User = {
    val authorities: Collection[_ <: GrantedAuthority] = loadAuthorities(user);
    val accountNonLocked = true
    val credentialsNonExpired = true
    val username = user.getAs[String]("email").get
    val password = user.getAs[String]("password").get
    val enabled = true
    val accountNonExpired = true

    new User(username, password, enabled,
      accountNonExpired, credentialsNonExpired, accountNonLocked,
      authorities, user.get("_id").toString())
  }

  private def loadAuthorities(user: MongoDBObject): Collection[_ <: GrantedAuthority] = {
    val roles = new ArrayList[GrantedAuthority]()
    user.getAs[String]("role") match {
      case Some(value) => roles.add(new GrantedAuthorityImpl(value))
      case None => Nil
    }
    roles
  }

}
