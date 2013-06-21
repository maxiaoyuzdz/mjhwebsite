import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object MjhwebsiteBuild extends Build {
  val Organization = "com.mxystudio"
  val Name = "mjhwebsite"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.0"
  val ScalatraVersion = "2.2.0"

  lazy val project = Project (
    "mjhwebsite",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
	  //===============sql=====================================
		"org.squeryl" %% "squeryl" % "0.9.5-6", 
		"mysql" % "mysql-connector-java" % "5.1.10",      // for MySQL, or use  5.1.10  5.1.22
		"c3p0" % "c3p0" % "0.9.1.2",
		//===============logger=====================================
		"org.clapper" % "grizzled-slf4j_2.10" % "1.0.1",
		//===============json=======================================
		"org.apache.commons" % "commons-digester3" % "3.0",
		//===============json=====================================
		"org.scalatra" %% "scalatra-json" % "2.2.0",
		"org.json4s" %% "json4s-native" % "3.2.3",
		"org.json4s" %% "json4s-jackson" % "3.2.3",
		"net.liftweb" %% "lift-json" % "2.5-RC1",
		//==========================================================
	  
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
