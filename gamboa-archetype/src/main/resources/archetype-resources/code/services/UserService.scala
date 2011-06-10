package code.services

import org.springframework.stereotype.Service
import com.mongodb.casbah.Imports._

trait UserService extends GenericService {
  def changeRole(user: String, role: String)
}

@Service
class UserServiceImpl extends DefaultService("users", "email") with UserService {

  override def changeRole(user: String, role: String) = {
    val dbo = find(user).get
    dbo.put("role", role)
    mongoColl += dbo
  }

}
