package code.db

import org.bson.Document
import org.mongodb.scala.{MongoClient, MongoDatabase}
import org.springframework.beans.factory.{DisposableBean, FactoryBean}
import org.springframework.util.Assert
import scala.beans.BeanProperty

/**
 * Spring `FactoryBean` that builds a `MongoDatabase` from the official
 * mongo-scala-driver. Replaces the old Casbah based `DBFactoryBean`.
 */
class MongoFactoryBean extends FactoryBean[MongoDatabase] with DisposableBean {

  @BeanProperty var host: String = "127.0.0.1"
  @BeanProperty var port: String = "27017"
  @BeanProperty var name: String = null
  @BeanProperty var username: String = null
  @BeanProperty var password: String = null

  private var client: MongoClient = _

  def getObject(): MongoDatabase = {
    Assert.notNull(name, "The MongoDB database name must be provided")

    val userPassNotNull =
      username != null && password != null && username.length() > 0 && password.length() > 0

    val uri =
      if (userPassNotNull) s"mongodb://$username:$password@$host:$port/$name"
      else s"mongodb://$host:$port"

    client = MongoClient(uri)
    client.getDatabase(name)
  }

  override def getObjectType: Class[_] = classOf[MongoDatabase]
  override def isSingleton: Boolean = true

  override def destroy(): Unit = if (client != null) client.close()
}
