package me.dmartyanov.system.utils

import com.typesafe.config.Config

import scala.language.implicitConversions


/**
  * Created by d.a.martyanov on 22.10.14.
  */
trait ConfigHelper {
  val config: Config

  implicit def exConfig(config: Config) = new ExtendedConfig(config)
}
