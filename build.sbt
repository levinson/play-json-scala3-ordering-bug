scalaVersion := "2.13.14"
// TODO: Test fails when using Scala 3
//scalaVersion := "3.3.3"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.10.5",
  //"org.playframework" %% "play-json" % "3.0.3", // same behavior
  "org.scalatest" %% "scalatest" % "3.2.18" % Test
)
