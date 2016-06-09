package me.dmartyanov.api.domain

/**
  * Created by d.a.martyanov on 22.10.14.
  */
case class RequiredResponse(
                             status: String = "OK",
                             results: Map[String, Int] = Map()
                           )