import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json._

case class Value(value: String) extends AnyVal

case class Model(key: Value, metadata: Option[String], region: Value)

object JsonSerializers {
  implicit val regionFmt: Format[Value] = Json.valueFormat[Value]
  implicit val modelFmt: Format[Model] = Json.format[Model]
}

class JsonSerializersTest extends AnyWordSpec with Matchers {

  import JsonSerializers._

  "JsonSerializers" should {
    "preserve ordering" in {
      val model = Model(Value("foo"), Some("metadata"), Value("region"))
      val serialized = Json.stringify(Json.toJson(model))
      println("serialized is " + serialized)
      serialized shouldBe """{"key":"foo","metadata":"metadata","region":"region"}"""
    }
  }
}
