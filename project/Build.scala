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
    dependencies = Seq(other),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Dependencies.dep_sockets
    )
  )

  lazy val other = Project(
    id = "Other",
    base = file("other"),
    dependencies = Seq(),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Dependencies.dep_other
    )
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

  lazy val baseSettings = Defaults.defaultSettings ++ Seq(
    libraryDependencies ++= Dependencies.dep_all_projects
  )

  lazy val defaultSettings = baseSettings ++ Seq(
    scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Ywarn-adapted-args"),
    javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:unchecked", "-Xlint:deprecation")
  )

}


object Dependencies {

  object Compile {
    val slf4j_api = "org.slf4j" % "slf4j-api" % "1.7.2"
    val slf4j_log4j = "org.slf4j" % "slf4j-log4j12" % "1.7.2"
    val grizzled = "org.clapper" %% "grizzled-slf4j" % "1.0.1"
  }

  object Test {
    val junit = "junit" % "junit" % "4.10" % "test"
  }

  val dep_sockets = Seq()

  val dep_other = Seq()

  val dep_testing = Seq(Test.junit)
  val dep_logging = Seq(Compile.grizzled, Compile.slf4j_api, Compile.slf4j_log4j)
  val dep_all_projects = dep_logging ++ dep_testing ++ Seq()

}
