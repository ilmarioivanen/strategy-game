ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "StrategyGame"
  )

// Add dependency on ScalaFX library
libraryDependencies += "org.scalafx" % "scalafx_3" % "19.0.0-R30"
// Add dependency on ScalaTest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15"
// Add dependency on XML
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0"
