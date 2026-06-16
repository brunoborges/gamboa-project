package code.services

import code.common.Record
import org.springframework.stereotype.Service

@Service
class UserService extends AbstractService("users") {

  def exists(email: String): Boolean = find(email).isDefined

  def changeRole(user: String, role: String): Unit =
    find(new Record() += ("email" -> user)) match {
      case Some(record) =>
        record.put("role", role)
        this += record
      case None =>
    }

}
