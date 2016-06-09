name := "spray-sample-rss"

version := "1.0"

scalaVersion := "2.11.2"

resolvers ++= Seq(
  "Maven Local"         at Path.userHome.toURI + ".m2/repository",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Releases"   at "http://repo.typesafe.com/typesafe/maven-releases/"
)

libraryDependencies ++=
    akka("2.3.4") ++
    spray("1.3.1") ++
  Seq(
    "org.scala-lang"            %   "scala-reflect"       % "2.11.2",
    "org.scalaj"                %%  "scalaj-http"          % "0.3.16",
    "org.json4s"                %%  "json4s-jackson"      % "3.2.10",
    "ch.qos.logback"            %   "logback-classic"     % "1.1.2",
    "org.scalatest"             %%  "scalatest"           % "2.2.0"     % "test"     
  )

def akka(v: String) = Seq(
  "com.typesafe.akka" %% "akka-actor"   % v,
  "com.typesafe.akka" %% "akka-remote"  % v,
  "com.typesafe.akka" %% "akka-slf4j"   % v,
  "com.typesafe.akka" %% "akka-testkit" % v % "test"
)

def spray(v : String) = Seq(
  "io.spray"            %%   "spray-can"     % v,
  "io.spray"            %%   "spray-client"  % v,
  "io.spray"            %%   "spray-routing" % v,
  "io.spray"            %%   "spray-testkit" % v  % "test"
)
