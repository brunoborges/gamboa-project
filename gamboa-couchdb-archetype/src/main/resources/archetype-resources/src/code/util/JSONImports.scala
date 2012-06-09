package code.util

import net.sf.json.JSONObject

object JSONImports {

  implicit def objectToJSON(obj: AnyRef): JSONObject = {
    val json = JSONObject.fromObject(obj)
    json.remove("id")
    json.remove("_id")
    json
  }

  implicit def jsonToObject[T](json: JSONObject)(implicit m: scala.reflect.Manifest[T]): T = {
    JSONObject.toBean(json, m.erasure).asInstanceOf[T]
  }

}
