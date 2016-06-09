package me.dmartyanov.rss

/**
  * Created by hellraiser on 10/24/14.
  */
trait LinksFilter {

  val secondDomainRegex = "(\\w|[а-я])+\\.(\\w|[а-я])+/"

  def domains = (linksList: List[String]) =>
    linksList
      .distinct
      .flatMap(extractDomain)
      .groupBy(l => l)
      .map { case (k, v) => k -> v.size }


  protected def extractDomain = (l: String) =>
    secondDomainRegex.r.findFirstIn(l)
      .map(_.dropRight(1))
      .toList
}
