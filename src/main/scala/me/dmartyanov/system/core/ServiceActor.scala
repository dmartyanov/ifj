package me.dmartyanov.system.core

import akka.actor.{Actor, ActorLogging, ActorRefFactory}
import me.dmartyanov.api.controllers.ApiHttpService

/**
  * Created by d.a.martyanov on 22.10.14.
  */
class ServiceActor extends Actor with ActorLogging
  with ApiHttpService {

  def actorRefFactory: ActorRefFactory = context

  override def receive: Receive = runRoute(serviceRoutes)
}
