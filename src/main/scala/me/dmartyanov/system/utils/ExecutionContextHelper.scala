package me.dmartyanov.system.utils

import scala.concurrent.ExecutionContext

/**
  * Created by d.a.martyanov on 22.10.14.
  */
trait ExecutionContextHelper {

  implicit def executionContext: ExecutionContext
}
