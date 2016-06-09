package me.dmartyanov.api.routing

import me.dmartyanov.api.controllers.MainController
import me.dmartyanov.system.utils.ExecutionContextHelper

/**
  * Created by d.a.martyanov on 22.10.14.
  */
trait MainModule {
  this: ExecutionContextHelper =>

  val paramName = "query"

  def processQuery(paramsMap: Map[String, List[String]]) =
    MainController.receiveQuery(paramsMap.getOrElse(paramName, List()))
}
