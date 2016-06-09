package me.dmartyanov.api.controllers

import me.dmartyanov.api.routing.{MainModule, MainRoute}
import me.dmartyanov.system.core.Global.{ConfigurationComponent, ServiceExecutionContext}
import me.dmartyanov.system.utils.{ExecutionContextHelper, LoggerHelper}
import spray.routing.HttpService

/**
  * Created by d.a.martyanov on 22.10.14.
  */

trait MainWebModule extends MainModule with MainRoute {
  this: ExecutionContextHelper =>
}

trait ApiHttpService extends HttpService
  with MainWebModule
  with LoggerHelper
  with ConfigurationComponent
  with ServiceExecutionContext {

  val serviceRoutes = mainRoute
}
