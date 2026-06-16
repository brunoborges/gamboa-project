package code.services

import code.common.Record
import org.bson.Document
import org.bson.types.ObjectId
import org.mongodb.scala._
import org.mongodb.scala.model.Filters
import org.springframework.beans.factory.annotation.Autowired
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.jdk.CollectionConverters._

/**
 * Base class for MongoDB backed services. Wraps the reactive mongo-scala-driver
 * with small, synchronous helpers so the service layer keeps a simple, blocking
 * programming model.
 */
class AbstractService(collectionName: String) {

  @Autowired
  var db: MongoDatabase = _

  protected def collection: MongoCollection[Document] =
    db.getCollection[Document](collectionName)

  protected def await[T](future: scala.concurrent.Future[T]): T =
    Await.result(future, 30.seconds)

  def find(): Seq[Record] =
    await(collection.find().toFuture()).map(new Record(_))

  def query(criteria: Map[String, Any]): Seq[Record] = {
    val filter = new Document(criteria.map { case (k, v) => k -> v.asInstanceOf[AnyRef] }.asJava)
    await(collection.find(filter).toFuture()).map(new Record(_))
  }

  def find(record: Record): Option[Record] =
    await(collection.find(record.doc).toFuture()).map(new Record(_)).headOption

  def find(email: String): Option[Record] =
    query(Map("email" -> email)).headOption

  def +=(record: Record): this.type = {
    Option(record.doc.get("_id")) match {
      case Some(id) => await(collection.replaceOne(Filters.eq("_id", id), record.doc).toFuture())
      case None     => await(collection.insertOne(record.doc).toFuture())
    }
    this
  }

  def +=(map: Map[String, Any]): this.type = this += Record(map)

  def -=(id: String): this.type = {
    await(collection.deleteOne(Filters.eq("_id", new ObjectId(id))).toFuture())
    this
  }
}
