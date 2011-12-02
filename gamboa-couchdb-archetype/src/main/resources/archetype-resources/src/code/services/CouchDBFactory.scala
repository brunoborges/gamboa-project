package code.services

import com.fourspaces.couchdb.{ Database, Session }

object CouchDBFactory {

  def getDatabase(name: String, session: Session): Database = {
    var db = session.getDatabase(name)
    if (db == null) {
      db = session.createDatabase(name)
    }
    db
  }

}
