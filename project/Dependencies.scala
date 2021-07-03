import sbt._

object Dependencies {
  object Versions {
    val scala212 = "2.12.14"
    val scala213 = "2.13.6"

    val trace4cats = "0.12.0-RC1+159-1f006820"

    val http4s = "0.23.0-RC1"
    val sttpClient3 = "3.3.9"
    val sttpModel = "1.4.7"
    val sttpTapir = "0.18.0-M15"

    val kindProjector = "0.13.0"
    val betterMonadicFor = "0.3.1"
  }

  lazy val trace4catsBase = "io.janstenpickle"           %% "trace4cats-base"            % Versions.trace4cats
  lazy val trace4catsCore = "io.janstenpickle"           %% "trace4cats-core"            % Versions.trace4cats
  lazy val trace4catsExporterCommon = "io.janstenpickle" %% "trace4cats-exporter-common" % Versions.trace4cats
  lazy val trace4catsHttp4sCommon = "io.janstenpickle"   %% "trace4cats-http4s-common"   % Versions.trace4cats
  lazy val trace4catsInject = "io.janstenpickle"         %% "trace4cats-inject"          % Versions.trace4cats
  lazy val trace4catsModel = "io.janstenpickle"          %% "trace4cats-model"           % Versions.trace4cats
  lazy val trace4catsTestkit = "io.janstenpickle"        %% "trace4cats-testkit"         % Versions.trace4cats

  lazy val http4sClient = "org.http4s" %% "http4s-client" % Versions.http4s

  lazy val sttpClient3 = "com.softwaremill.sttp.client3"       %% "cats"                % Versions.sttpClient3
  lazy val sttpClient3Http4s = "com.softwaremill.sttp.client3" %% "http4s-backend"      % Versions.sttpClient3
  lazy val sttpModel = "com.softwaremill.sttp.model"           %% "core"                % Versions.sttpModel
  lazy val sttpTapirCats = "com.softwaremill.sttp.tapir"       %% "tapir-cats"          % Versions.sttpTapir
  lazy val sttpTapirJsonCirce = "com.softwaremill.sttp.tapir"  %% "tapir-json-circe"    % Versions.sttpTapir
  lazy val sttpTapirHttp4s = "com.softwaremill.sttp.tapir"     %% "tapir-http4s-server" % Versions.sttpTapir

  lazy val kindProjector = ("org.typelevel" % "kind-projector"     % Versions.kindProjector).cross(CrossVersion.full)
  lazy val betterMonadicFor = "com.olegpy" %% "better-monadic-for" % Versions.betterMonadicFor
}
