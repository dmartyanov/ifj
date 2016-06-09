package me.dmartyanov.system.utils

import org.slf4j.LoggerFactory

/**
  * Created by d.a.martyanov on 22.10.14.
  */
trait LoggerHelper {
  self: LoggerHelper =>

  val ERROR = 1
  val WARN = 2
  val INFO = 3

  val lf = LoggerFactory.getLogger(self.getClass)

  def log(msg: String, level: Int) = level match {
    case ERROR => lf.error(msg)
    case WARN => lf.warn(msg)
    case INFO => lf.info(msg)
    case level: Int => lf.debug(s"Error level[$level] - message: [$msg]")
  }

  def logError(err: Throwable, level: Int) = level match {
    case ERROR => lf.error(err.getMessage, err)
    case WARN => lf.warn(err.getMessage, err)
    case INFO => lf.info(err.getMessage, err)
    case level: Int => lf.debug(s"Error level[$level] - message: [$err.getMessage]", err)
  }

  def logWarn[T](msg: String)(default: => T) = {
    lf.warn(msg)
    default
  }
}
