package code.services

import org.springframework.stereotype.Service
import com.mongodb.casbah.Imports._

@Service
class UserService extends AbstractService("users") {

  override def changeRole(user: String, role: String) = {
    val r = new Record()
    r += "email" -> user
    val col = getCollection()
    val dbo = find(r).get
    dbo.put("role", role)
    col += dbo
  }

}
