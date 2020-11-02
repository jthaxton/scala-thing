name := """event_app"""
// organization := "events"

// // version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
// val AkkaVersion = "2.6.10"
scalaVersion := "2.12.6"

version := "1.0"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.6.5",
  "com.typesafe.akka" %% "akka-testkit" % "2.6.5" % Test,
  "com.typesafe.akka" %% "akka-persistence" % "2.6.5",
  "org.json4s" %% "json4s-native" % "3.6.0",
  "com.okumin" %% "akka-persistence-sql-async" % "0.5.1",
  "com.github.mauricio" %% "postgresql-async" % "0.2.+",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  // "org.slf4j" % "slf4j-simple" % "1.7.25",
)