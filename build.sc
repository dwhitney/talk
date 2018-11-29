import mill._, scalalib._

object talk extends ScalaModule{

  def scalaVersion = "2.12.7"

  def scalacPluginIvyDeps = Agg(
    ivy"org.spire-math::kind-projector:0.9.8"
  )
  
  def ivyDeps = Agg(
    ivy"org.typelevel::cats-core:1.4.0"
  )
 
  def scalaOptions = Seq(
    "-Ypartial-unification",
    "-languageigherKinds"
  )
}
