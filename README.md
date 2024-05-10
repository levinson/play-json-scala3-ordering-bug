# Play-Json Scala3 Ordering Bug

This project demonstrates a bug with field ordering in the play-json library when compiling with Scala 3.

Affected versions:
* "com.typesafe.play" %% "play-json" % "2.10.5"
* "org.playframework" %% "play-json" % "3.0.3"

See [build.sbt](build.sbt) and [JsonSerializersTest](src/test/scala/JsonSerializersTest.scala) for more details.

The issue has also been reported to play-json library: https://github.com/playframework/play-json/issues/1038
