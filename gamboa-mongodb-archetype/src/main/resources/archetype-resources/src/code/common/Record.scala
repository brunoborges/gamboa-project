package code.common

import org.bson.Document
import scala.jdk.CollectionConverters._

/**
 * Thin, mutable wrapper around a BSON `org.bson.Document`. Replaces the old
 * Casbah `MongoDBObject` abstraction while keeping a Scala friendly, map-like
 * API.
 */
class Record(val doc: Document) extends Serializable {

  def this() = this(new Document())

  def +=(kv: (String, Any)): this.type = { doc.put(kv._1, kv._2.asInstanceOf[AnyRef]); this }
  def -=(key: String): this.type = { doc.remove(key); this }

  def put(key: String, value: Any): Unit = doc.put(key, value.asInstanceOf[AnyRef])
  def contains(key: String): Boolean = doc.containsKey(key)

  def get[T](key: String): T = doc.get(key).asInstanceOf[T]
  def getOpt[T](key: String): Option[T] = Option(doc.get(key)).map(_.asInstanceOf[T])

  def id: String = Option(doc.get("_id")).map(_.toString).orNull

  def fields: Map[String, AnyRef] = doc.asScala.toMap
}

object Record {
  def apply(map: Map[String, Any]): Record = {
    val record = new Record()
    map.foreach { case (k, v) => record.put(k, v) }
    record
  }
}
