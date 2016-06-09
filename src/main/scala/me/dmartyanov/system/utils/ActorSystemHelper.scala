package me.dmartyanov.system.utils

import akka.actor.ActorSystem

/**
  * Created by d.a.martyanov on 22.10.14.
  */
trait ActorSystemHelper {
  implicit def actorSystem: ActorSystem
}
