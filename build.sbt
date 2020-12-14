name := "wth-anki"

version := "0.0.1-SNAPSHOT"

organization in ThisBuild := "io.funkyminds"

scalaVersion := "2.13.4"

scalacOptions := Seq("-unchecked", "-deprecation")

val circeVersion = "0.12.3"
val http4sVersion = "0.21.4"
val zioVersion = "1.0.3"
val zioConfigVersion = "1.0.0-RC29"

//@formatter:off
libraryDependencies ++= Seq(
  "io.funkyminds"       %%  "wth-core"            % "0.0.1-SNAPSHOT",
  "dev.zio"             %%  "zio"                 % zioVersion,
  "dev.zio"             %%  "zio-streams"         % zioVersion,
  "dev.zio"             %%  "zio-interop-cats"    % "2.2.0.1",
  "dev.zio"             %%  "zio-config"          % zioConfigVersion,
  "dev.zio"             %%  "zio-config-magnolia" % zioConfigVersion,
  "dev.zio"             %%  "zio-config-typesafe" % zioConfigVersion,
  "io.circe"            %%  "circe-core"          % circeVersion,
  "io.circe"            %%  "circe-generic"       % circeVersion,
  "io.circe"            %%  "circe-parser"        % circeVersion,
  "org.http4s"          %%  "http4s-dsl"          % http4sVersion,
  "org.http4s"          %%  "http4s-blaze-server" % http4sVersion,
  "org.http4s"          %%  "http4s-blaze-client" % http4sVersion,
  "org.http4s"          %%  "http4s-circe"        % http4sVersion
)
//@formatter:on
