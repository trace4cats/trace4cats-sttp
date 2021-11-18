lazy val commonSettings = Seq(
  Compile / compile / javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
  libraryDependencies ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, _)) =>
        Seq(compilerPlugin(Dependencies.kindProjector), compilerPlugin(Dependencies.betterMonadicFor))
      case _ => Seq.empty
    }
  },
  scalacOptions += "-Wconf:any:wv",
  Test / fork := true,
  resolvers += Resolver.sonatypeRepo("releases"),
)

lazy val noPublishSettings =
  commonSettings ++ Seq(publish := {}, publishArtifact := false, publishTo := None, publish / skip := true)

lazy val publishSettings = commonSettings ++ Seq(
  publishMavenStyle := true,
  pomIncludeRepository := { _ =>
    false
  },
  Test / publishArtifact := false
)

lazy val root = (project in file("."))
  .settings(noPublishSettings)
  .settings(name := "Trace4Cats Sttp")
  .aggregate(`sttp-client3`, `sttp-common`, `sttp-tapir`)

lazy val `sttp-client3` = (project in file("modules/sttp-client3"))
  .settings(publishSettings)
  .settings(
    name := "trace4cats-sttp-client3",
    libraryDependencies ++= Seq(Dependencies.trace4catsBase, Dependencies.trace4catsCore, Dependencies.sttpClient3),
    libraryDependencies ++= Seq(
      Dependencies.http4sDsl,
      Dependencies.trace4catsExporterCommon,
      Dependencies.trace4catsInject,
      Dependencies.sttpClient3Http4s
    ).map(_ % Test)
  )
  .dependsOn(`sttp-common` % "compile->compile;test->test")

lazy val `sttp-common` = (project in file("modules/sttp-common"))
  .settings(publishSettings)
  .settings(
    name := "trace4cats-sttp-common",
    libraryDependencies ++= Seq(Dependencies.trace4catsModel, Dependencies.sttpModel),
    libraryDependencies ++= Seq(
      Dependencies.trace4catsTestkit,
      Dependencies.trace4catsBase,
      Dependencies.trace4catsCore
    ).map(_ % Test)
  )

lazy val `sttp-tapir` = (project in file("modules/sttp-tapir"))
  .settings(publishSettings)
  .settings(
    name := "trace4cats-sttp-tapir",
    libraryDependencies ++= Seq(Dependencies.trace4catsInject, Dependencies.sttpTapirCats),
    libraryDependencies ++= Seq(
      Dependencies.http4sClient,
      Dependencies.http4sDsl,
      Dependencies.sttpTapirJsonCirce,
      Dependencies.trace4catsExporterCommon,
      Dependencies.sttpTapirHttp4s,
      Dependencies.logback
    ).map(_ % Test)
  )
  .dependsOn(`sttp-common` % "compile->compile;test->test")
