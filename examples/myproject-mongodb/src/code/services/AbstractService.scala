package code.services

import org.springframework.beans.factory.annotation.Autowired
import com.mongodb.casbah.MongoDB
import com.mongodb.{DBObject, DBCollection, BasicDBObjectBuilder}

class AbstractService(collection: String) {

  @Autowired
  var db: MongoDB = _

  def getCollection(cname: String = collection) :DBCollection = db.getCollection(cname)

}
