import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

val Scala213 = "$scala_version$"

ThisBuild / crossScalaVersions := Seq("$other_scala_version$", Scala213)
ThisBuild / scalaVersion := Scala213

ThisBuild / organization := "$organization$"
ThisBuild / baseVersion := "0.1"

ThisBuild / testFrameworks += new TestFramework("munit.Framework")

ThisBuild / publishGithubUser := "$contributorUsername$"
ThisBuild / publishFullName := "$contributorName$"

val http4sV = "0.23.6"
val munitCatsEffectV = "1.0.6"

lazy val `$name$` = project.in(file("."))
  .enablePlugins(NoPublishPlugin)
  .aggregate(core.jvm, core.js)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "$name$",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-core" % http4sV,
      "org.typelevel" %%% "munit-cats-effect-3" % munitCatsEffectV % Test,
    )
  ).jsSettings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)},
  )
