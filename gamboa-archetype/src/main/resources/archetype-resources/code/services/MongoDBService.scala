package code.services

import scala.collection.mutable.MutableList
import scala.reflect.BeanProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.annotation.Autowired

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.Implicits._

trait GenericService {
  def +=(obj: Map[String, Object]): Option[MongoDBObject]
  def -=(_id: String)
  def find(uniqueValue: String): Option[MongoDBObject]
  def get(_id: String): Option[MongoDBObject]
  def exists(uniqueValue: String): Boolean
  def find(): List[MongoDBObject]
  def query(queryObj: Map[String, Object], fields: String*): List[MongoDBObject]
}

abstract class AbstractService(defaultCollName: String) {

  @Value("${mdb.host}")
  var mongoDbHost: String = _

  @Value("${mdb.port}")
  var mongoDbPort: Integer = _

  @Value("${mdb.name}")
  var mongoDbName: String = _

  def mongoConn = MongoConnection(mongoDbHost, mongoDbPort)(mongoDbName)
  def mongoColl = mongoConn(defaultCollName)
}

abstract class DefaultService(defaultCollName: String, uniqueKey: String)
  extends AbstractService(defaultCollName) with GenericService {

  def getUniqueKey: String = uniqueKey

  def find(): List[MongoDBObject] = {
    val resultList = new MutableList[MongoDBObject]()
    mongoColl.find().foreach { x => resultList += x }
    resultList.toList
  }

  def +=(obj: Map[String, Object]): Option[MongoDBObject] = {
    if (exists(obj.get(uniqueKey).toString())) {
      None
    } else {
      val dbo = obj.asDBObject
      mongoColl += dbo
      Some(dbo)
    }
  }

  def -=(_id: String) = {
    mongoColl.remove(MongoDBObject("_id" -> new ObjectId(_id)))
  }

  def find(uniqueValue: String): Option[MongoDBObject] = {
    mongoColl.findOne(MongoDBObject(uniqueKey -> uniqueValue)) match {
      case Some(value) => {
        val obj: MongoDBObject = value
        Some(value)
      }
      case None => None
    }
  }

  def get(_id: String): Option[MongoDBObject] = {
    mongoColl.findOneByID(new ObjectId(_id)) match {
      case Some(value) => {
        val obj: MongoDBObject = value
        Option(value)
      }
      case None => None
    }
  }

  def exists(uniqueValue: String): Boolean = {
    mongoColl.count(MongoDBObject(uniqueKey -> uniqueValue)) > 0;
  }

  def query(queryObj: Map[String, Object], fields: String*): List[MongoDBObject] = {
    if (fields == null || fields.length == 0) {
      return Nil
    }

    val mQuery = if (queryObj == null) queryObj.asDBObject else MongoDBObject.empty
    val mFields = MongoDBObject.newBuilder
    fields.foreach { field => mFields += field -> 1 }
    val mFieldObj = mFields.result
    val resultList = new MutableList[MongoDBObject]()
    mongoColl.find(mQuery, mFieldObj).foreach { x => resultList += x }
    resultList.toList
  }

}