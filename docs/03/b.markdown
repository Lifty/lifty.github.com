# Creating Your Templates

Templates are just plain text files with `.ssp` extensions. You have to use the `.ssp` extension for legacy reasons right now. Templates are quite simple, you can define 
placeholders for argument values that will be substituted when the template is rendered and you can define injection points. The injection points are places in the template
where other templates might want to inject something when the template is rendered.

Here's a very simple example showing placeholder values (the ones in \${x} where x is the name of the argument it's a placeholder for): 

	package \${snippetpack}

	import scala.xml.{NodeSeq}
	import net.liftweb.util.Helpers._

	class \${snippetName} {
	  def render = "*" #> <strong>hello world!</strong>
	}

and here's an example using injection points, the syntax is //#inject point: nameOfInjectionPoint: 

	name := "\${projectName}"

	scalaVersion := "\${scalaVersion}"

	libraryDependencies += "net.liftweb" %% "lift-webkit" % "\${liftversion}" % "compile->default"

	//#inject point: dependencies

	resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

	seq(webSettings :_*)

	libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "test,container"

	libraryDependencies += "org.scala-tools.testing" %% "specs" % "1.6.9" % "test"
