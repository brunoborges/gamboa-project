package code.services

import com.mongodb.DBCollection
import code.Record
import com.mongodb.DB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.collection.JavaConversions._

trait UserService extends GenericService {

  final def collection: String = "users"

}

@Service
class UserServiceImpl extends AbstractService with GenericService with UserService {

  @Autowired
  var db: DB = _

  def collection: String = "users"

  //def collection: DBCollection = db.getCollection("users")

  /*
    def adicionarConvidado(idEvento: String, convidado: Record) {
    val dbo = BasicDBObjectBuilder.start(convidado).add("idEvento", idEvento).get();
    col.save(dbo);
  }

  def listarConvidados(idEvento: String): List[Record] = {
    val existente: DBCursor = col.find(BasicDBObjectBuilder.start(
      "idEvento", idEvento).get())

    if (existente != null) {
      val convidados = new ArrayList[Record]()

      for (row: DBObject <- asScalaIterator(existente)) {
        val convidado = new Record()
        convidados.add(convidado)
        convidado.put("idEvento", idEvento)

        for (key <- row.keySet)
          convidado.put(key, row.get(key))
      }

      return convidados.toList
    }

    return null
  }

  def removerConvidado(idConvidado: String) {
    col.remove(BasicDBObjectBuilder.start("_id", new ObjectId(idConvidado)).get())
  }
  */
}
