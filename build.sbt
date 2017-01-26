organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"

lazy val `lagom-client-demo` = (project in file("."))
  .settings(name := "lagom-client-demo")
  .aggregate(`httpbin-api`, `lagom-client-main`)

lazy val `httpbin-api` = (project in file("httpbin-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-client-main` = (project in file("lagom-client-main"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslClient
    )
  ).dependsOn(`httpbin-api`)
