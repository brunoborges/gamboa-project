package code.db

import org.springframework.beans.factory.FactoryBean
import org.springframework.util.Assert
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.MongoDB
import scala.reflect.BeanProperty

class DBFactoryBean extends FactoryBean[MongoDB] {

  @BeanProperty var mongo: MongoConnection = null
  @BeanProperty var name: String = null
  @BeanProperty var username: String = null
  @BeanProperty var password: String = null

  def getObject(): MongoDB = {
    Assert.notNull(mongo);
    Assert.notNull(name);
    
    val db = mongo.getDB(name)
    val userPassNotNull = username != null && password != null && username.length() > 0 && password.length() > 0
    if (db.isAuthenticated == false && userPassNotNull) db.authenticate(username, password)
    db
  }

  override def getObjectType = classOf[MongoDB]
  override def isSingleton = true

}
