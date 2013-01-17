import sbt._
import sbt.Keys._

object Build extends Build {


  lazy val root = Project(
    id = "Networking",
    base = file(".")
  ) aggregate(sockets, other)

  lazy val sockets = Project(
    id = "Sockets",
    base = file("sockets"),
    //dependencies = Seq(Dependencies.dep_sockets),
    settings = defaultSettings
  )

  lazy val other = Project(
    id = "Other",
    base = file("other"),
    //dependencies = Seq(Dependencies.dep_other),
    settings = defaultSettings
  )

  override lazy val settings =
    super.settings ++
      buildSettings ++
      Seq(
        shellPrompt := {
          s => Project.extract(s).currentProject.id + " > "
        }
      )

  lazy val buildSettings = Seq(
    organization := "org.suggs",
    version := "1.0",
    scalaVersion := "2.10.0"
  )

  lazy val baseSettings =  Defaults.defaultSettings

  lazy val defaultSettings = baseSettings ++ Seq(
    scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Ywarn-adapted-args"),
    javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:unchecked", "-Xlint:deprecation")
  )

}


object Dependencies {

  object Compile {
    val slf4jApi = "org.slf4j" % "slf4j-api" % "1.7.2"
  }

  object Test {
    val junit = "junit" % "junit" % "4.10" % "test"
  }

  val dep_sockets = Seq(Compile.slf4jApi, Test.junit)

  val dep_other = Seq(Compile.slf4jApi, Test.junit)

}
