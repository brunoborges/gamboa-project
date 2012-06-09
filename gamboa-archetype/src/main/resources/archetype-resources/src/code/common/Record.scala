package code.common

import scala.collection.JavaConversions.{mapAsJavaMap, asScalaSet}

import java.util.Map
import java.util.HashMap

import org.bson.types.ObjectId

import com.mongodb.casbah.Imports.wrapDBObj
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{DBObject, BasicDBObjectBuilder}

class Record extends HashMap[String, Any] with Serializable with java.io.Serializable {
  def this(map: Map[String, Any]) = { this(); map.keySet().foreach(x ⇒ put(x, map.get(x))) }
  def +=(kv: (String, Any)): this.type = { put(kv._1, kv._2); this }
  def -=(key: String): this.type = { remove(key); this }
  def <[T](key: String)(implicit m: scala.reflect.Manifest[T]): T = {
    val obj = super.get(key)
    if (obj != null && !obj.isInstanceOf[String] && m.erasure == classOf[String])
      obj.toString().asInstanceOf[T]
    else
      super.get(key).asInstanceOf[T]
  }
  def get[T](key: String)(implicit m: scala.reflect.Manifest[T]): T = <[T](key)
  def id = get[String]("_id")
}

object Record {
  implicit def record2dbo(record: Record): DBObject = {
    val dbo = BasicDBObjectBuilder.start(record).get()
    if (record.containsKey("_id"))
      dbo.put("_id", new ObjectId(record.get[String]("_id")))
    dbo
  }
  implicit def dbo2record(dbo: DBObject): Record = {
    val mdbo: MongoDBObject = dbo
    val record = new Record(mdbo)
    record
  }
}
