package code

import com.mongodb.DBObject
import java.util.HashMap
import java.util.Map
import scala.collection.JavaConversions._

class Record extends HashMap[String, Object] {

  def this(map: Map[String, Object]) = {
    this();

    for (key: String <- map.keySet())
      put(key, map.get(key))
  }

  def +=(kv: (String, Object)): this.type = {
    put(kv._1, kv._2)
    this
  }

  def -=(key: String): this.type = {
    remove(key)
    this
  }

  def <(key: String): Object = { get(key) }

}
