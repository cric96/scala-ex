name := "excercies"

version := "0.1"

lazy val ex1 = (project in file("exercise-1"))
  .settings(libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test")

lazy val ex2 = (project in file("exercise-2"))
  .settings(libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test")

lazy val ex3 = (project in file("exercise-3"))
  .settings(libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test")

