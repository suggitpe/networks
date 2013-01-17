import sbt._
import sbt.Keys._

object Build extends Build {

  override lazy val settings =
    super.settings ++
    buildSettings ++
    Seq(
      shellPrompt := { s => Project.extract(s).currentProject.id + " > "}
    )

  lazy val buildSettings = Seq(
    organization := "org.suggs",
    version := "1.0"
  )

  lazy val root = Project(
    id = "Networking",
    base = file(".")
  ) aggregate(sockets, other)

  lazy val sockets = Project(
    id = "Sockets",
    base = file("sockets")
  )

  lazy val other = Project(
    id = "Other",
    base = file("other")
  )


}

object Dependencies {

  object Compile {
    val slf4jApi = "org.slf4j" % "slf4j-api" % "1.7.2"
  }

  object Test {
    val junit = "junit" % "junit" % "4.10" % "test"
  }

  val sockets = Seq(Compile.slf4jApi, Test.junit)

  val other = Seq(Compile.slf4jApi, Test.junit)

}
