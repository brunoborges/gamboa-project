package code.services

import scala.reflect.BeanProperty
import org.springframework.beans.factory.annotation.Value
import com.mongodb.DB
import org.springframework.beans.factory.annotation.Autowired

import com.mongodb.casbah.Imports._

abstract class AbstractService {

  @Value("#{db.host}")
  @BeanProperty
  var mongoDbHost: String = _

  @Value("#{db.port}")
  @BeanProperty
  var mongoDbPort: Integer = _

  @Value("#{db.name}")
  @BeanProperty
  var mongoDbName: String = _

  def mongoConn = MongoConnection(mongoDbHost, mongoDbPort)(mongoDbName)

}
