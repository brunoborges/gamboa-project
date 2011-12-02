package code.services

import org.springframework.beans.factory.annotation.Autowired
import com.fourspaces.couchdb.Database

trait CouchDBService {

  @Autowired var couchDB: Database = _

}
