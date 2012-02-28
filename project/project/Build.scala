import sbt._

object PluginDef extends Build {
  override def projects = Seq(root)
  lazy val root = Project("plugins", file(".")) dependsOn (pamflet)

  lazy val pamflet = uri("git://github.com/n8han/pamflet-plugin#0.4.0")
}