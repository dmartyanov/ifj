package me.dmartyanov.api.routing

import me.dmartyanov.system.utils.ExecutionContextHelper
import org.json4s.{DefaultFormats, Formats}
import spray.httpx.Json4sJacksonSupport
import spray.routing.HttpService

/**
  * Created by d.a.martyanov on 22.10.14.
  */
trait MainRoute extends HttpService with Json4sJacksonSupport {
  this: MainModule with ExecutionContextHelper =>

  override implicit def json4sJacksonFormats: Formats = DefaultFormats

  val mainRoute = {
    path("search") {
      get {
        parameterMultiMap { params =>
          onComplete(processQuery(params)) {
            complete(_)
          }
        }
      }
    }
  }
}
