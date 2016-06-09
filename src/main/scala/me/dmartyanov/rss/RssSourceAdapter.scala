package me.dmartyanov.rss

import java.net.URLEncoder

import me.dmartyanov.system.core.Global.ExternalRequestExecutionContext
import me.dmartyanov.system.utils.{ActorSystemHelper, ConfigHelper, LoggerHelper}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.xml.{Elem, XML}


/**
  * Created by d.a.martyanov on 22.10.14.
  */
trait RssSourceAdapter extends ExternalRequestExecutionContext with ConfigHelper {
  this: LoggerHelper with ActorSystemHelper =>

  val numDoc = Try(config.getInt("app.numdoc")).getOrElse(10)

  def rssQueryLinks(queries: List[String]): Future[List[String]] =
    Future sequence {
      queries map getRssLinks
    } map {
      _.flatten
    }

  def getRssLinks(query: String) =
    Future {
      (getXmlSource andThen parseXMLtoLinks) (query)
    }

  def parseXMLtoLinks(elemSafe: Try[Elem]): List[String] =
    elemSafe map {
      xmlRss => for {
        item <- xmlRss \\ "item"
        link <- item \ "link"
      } yield link.text
    } match {
      case Success(list) => list toList
      case Failure(err) =>
        log(err.getMessage, ERROR)
        List.empty[String]
    }

  def getXmlSource = (query: String) => Try {
    XML.loadString(scala.io.Source.fromURL(sourceUrlString(query)).getLines().mkString)
  }

  def sourceUrlString = (q: String) =>
    s"https://blogs.yandex.ru/search.rss?text=${URLEncoder.encode(q, "utf-8")}&numdoc=$numDoc"

}
