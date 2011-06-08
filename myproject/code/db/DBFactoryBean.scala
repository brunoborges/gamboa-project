package code.db

import com.mongodb.Mongo
import com.mongodb.DB
import org.springframework.beans.factory.FactoryBean
import org.springframework.util.Assert

class DBFactoryBean extends FactoryBean[DB] {

  var mongo: Mongo = null
  var name: String = null

  def getObject(): DB = {
    Assert.notNull(mongo);
    Assert.notNull(name);
    return mongo.getDB(name);
  }

  def getObjectType = classOf[DB]
  def isSingleton = true
  def setMongo(mongo: Mongo) = this.mongo = mongo
  def setName(name: String) = this.name = name

}
