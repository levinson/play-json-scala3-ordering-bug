import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json._

case class KeyDef(foo: String, bar: String) {
  override def toString: String = s"$foo.$bar"
}

object KeyDef {
  def fromString(value: String): JsResult[KeyDef] = {
    val values = value.split(".")
    if (values.length != 2) {
      JsError(s"Failed to parse KeyDef from: $value")
    } else {
      JsSuccess(KeyDef(values(0), values(1)))
    }
  }
}

case class Region(value: String) extends AnyVal

case class Model(key: KeyDef, metadata: Option[String], region: Region)

object JsonSerializers {

  implicit val keyDefFmt: Format[KeyDef] = new Format[KeyDef] {
    override def reads(json: JsValue): JsResult[KeyDef] =
      json.validate[String].flatMap(KeyDef.fromString)

    override def writes(id: KeyDef): JsValue = JsString(id.toString)
  }

  implicit val regionFmt: Format[Region] = Json.valueFormat[Region]

  implicit val modelFmt: Format[Model] = Json.format[Model]
}

class JsonSerializersTest extends AnyWordSpec with Matchers {

  import JsonSerializers._

  "JsonSerializers" should {
    "preserve ordering" in {
      val model = Model(KeyDef("foo", "bar"), Some("metadata"), Region("region"))
      val serialized = Json.stringify(Json.toJson(model))
      println("serialized is " + serialized)
      serialized shouldBe """{"key":"foo.bar","metadata":"metadata","region":"region"}"""
    }
  }
}
