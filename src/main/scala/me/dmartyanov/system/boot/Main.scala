package me.dmartyanov.system.boot

import akka.actor.Props
import akka.io.IO
import me.dmartyanov.system.core.Global.{ActorSystemComponentImpl, ConfigurationComponent}
import me.dmartyanov.system.core.ServiceActor
import spray.can.Http
import spray.routing.SimpleRoutingApp

/**
  * Created by d.a.martyanov on 22.10.14.
  */
object Main extends App
  with SimpleRoutingApp
  with ActorSystemComponentImpl
  with ConfigurationComponent {

  val handler = actorSystem.actorOf(Props[ServiceActor], name = "handler")
  IO(Http) ! Http.Bind(handler, interface = "0.0.0.0", port = config.get[Int]("app.port", 9000))
}
