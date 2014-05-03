name := "pingball"

javaSource in Compile := baseDirectory.value / "src" / "main"

unmanagedBase := baseDirectory.value

javaSource in Test := baseDirectory.value / "src" / "test"

javaOptions in Test += "-enableassertions"

javacOptions in (Compile, compile) ++= Seq("-Xlint:all")

javacOptions in doc ++= Seq("-quiet")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11",
  "com.novocode" % "junit-interface" % "0.10" % "test"
)

autoScalaLibrary := false

fork in Test := true
