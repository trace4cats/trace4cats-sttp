import sbt._

object Dependencies {
  object Versions {
    val scala212 = "2.12.16"
    val scala213 = "2.13.8"
    val scala3 = "3.1.3"

    val trace4cats = "0.14.0"

    val http4s = "0.23.14"
    val logback = "1.2.11"
    val sttpClient3 = "3.7.2"
    val sttpModel = "1.5.4"
    val sttpTapir = "1.0.3"

    val kindProjector = "0.13.2"
    val betterMonadicFor = "0.3.1"
  }

  lazy val trace4catsContextUtils = "io.janstenpickle" %% "trace4cats-context-utils" % Versions.trace4cats
  lazy val trace4catsCore = "io.janstenpickle"         %% "trace4cats-core"          % Versions.trace4cats
  lazy val trace4catsKernel = "io.janstenpickle"       %% "trace4cats-kernel"        % Versions.trace4cats
  lazy val trace4catsTestkit = "io.janstenpickle"      %% "trace4cats-testkit"       % Versions.trace4cats

  lazy val http4sClient = "org.http4s"                         %% "http4s-client"       % Versions.http4s
  lazy val http4sDsl = "org.http4s"                            %% "http4s-dsl"          % Versions.http4s
  lazy val logback = "ch.qos.logback"                           % "logback-classic"     % Versions.logback
  lazy val sttpClient3 = "com.softwaremill.sttp.client3"       %% "cats"                % Versions.sttpClient3
  lazy val sttpClient3Http4s = "com.softwaremill.sttp.client3" %% "http4s-backend"      % Versions.sttpClient3
  lazy val sttpModel = "com.softwaremill.sttp.model"           %% "core"                % Versions.sttpModel
  lazy val sttpTapirCats = "com.softwaremill.sttp.tapir"       %% "tapir-cats"          % Versions.sttpTapir
  lazy val sttpTapirJsonCirce = "com.softwaremill.sttp.tapir"  %% "tapir-json-circe"    % Versions.sttpTapir
  lazy val sttpTapirHttp4s = "com.softwaremill.sttp.tapir"     %% "tapir-http4s-server" % Versions.sttpTapir

  lazy val kindProjector = ("org.typelevel" % "kind-projector"     % Versions.kindProjector).cross(CrossVersion.full)
  lazy val betterMonadicFor = "com.olegpy" %% "better-monadic-for" % Versions.betterMonadicFor
}
