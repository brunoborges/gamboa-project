package code.services

import code.Record

trait SignUpService {

  def signUp(user: Record)

  def emailExists(email: String): Boolean

  def loadUser(email: String): Record

}
