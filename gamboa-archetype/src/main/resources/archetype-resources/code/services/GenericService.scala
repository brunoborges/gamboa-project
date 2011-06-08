package code.services

import com.mongodb.DBCollection
import code.Record
import com.mongodb.DB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

trait GenericService {

  def collection: String

  def +=(record: Record) = { 
  }

  def <(softId: String): Record {
  }

  def -=(hardId: String) {
  }

  def iterator: Iterator[Record] {
  }

}
