import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json._

case class Region(value: String) extends AnyVal

case class Model(metadata: Option[String], region: Region)

object JsonSerializers {
  implicit val regionFmt: Format[Region] = Json.valueFormat[Region]
  implicit val modelFmt: Format[Model] = Json.format[Model]
}

class JsonSerializersTest extends AnyWordSpec with Matchers {

  import JsonSerializers._

  "JsonSerializers" should {
    "preserve ordering" in {
      val model = Model(Some("metadata"), Region("region"))
      val serialized = Json.stringify(Json.toJson(model))
      println("serialized is " + serialized)
      serialized shouldBe """{"metadata":"metadata","region":"region"}"""
    }
  }
}
