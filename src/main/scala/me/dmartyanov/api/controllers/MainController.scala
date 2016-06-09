package me.dmartyanov.api.controllers

import me.dmartyanov.api.domain.RequiredResponse
import me.dmartyanov.rss.{LinksFilter, RssSourceAdapter}
import me.dmartyanov.system.core.Global.{ActorSystemComponentImpl, ConfigurationComponent, ServiceExecutionContext}
import me.dmartyanov.system.utils.LoggerHelper

import scala.concurrent.Future

/**
  * Created by d.a.martyanov on 22.10.14.
  */
object MainController extends ServiceExecutionContext
  with RssSourceAdapter
  with LinksFilter
  with LoggerHelper
  with ConfigurationComponent
  with ActorSystemComponentImpl {

  def receiveQuery(queries: List[String]): Future[RequiredResponse] =
    rssQueryLinks(queries)
      .map(res => RequiredResponse(results = domains(res)))
      .recover {
        case err: Exception =>
          log(err.getMessage, ERROR)
          RequiredResponse(status = "ERROR")
      }
}
