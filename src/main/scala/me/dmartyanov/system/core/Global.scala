package me.dmartyanov.system.core

import java.util.concurrent.{LinkedBlockingDeque, ThreadPoolExecutor, TimeUnit}

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import me.dmartyanov.system.utils.{ActorSystemHelper, ConfigHelper, ExecutionContextHelper}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.implicitConversions
import scala.util.Try

/**
  * Created by d.a.martyanov on 22.10.14.
  */
object Global {

  private lazy val configurationImpl = ConfigFactory.load()

  trait ConfigurationComponent extends ConfigHelper {
    override val config = configurationImpl
  }

  private lazy val actors = ActorSystem("ifj-as", configurationImpl)

  trait ActorSystemComponentImpl extends ActorSystemHelper {
    override implicit def actorSystem: ActorSystem = actors
  }

  trait ServiceExecutionContext extends ExecutionContextHelper {
    override implicit def executionContext: ExecutionContext = actors.dispatcher
  }

  lazy val externalRequestExecutionPool = ExecutionContext.fromExecutorService(
    new ThreadPoolExecutor(
      Runtime.getRuntime.availableProcessors(),
      Try(configurationImpl.getInt("http.execution.maxPoolSize")).getOrElse(10),
      (Try(configurationImpl.getInt("http.execution.keepAlive")).getOrElse(1) minute) toMillis,
      TimeUnit.MILLISECONDS,
      new LinkedBlockingDeque[Runnable]()
    )
  )

  trait ExternalRequestExecutionContext extends ExecutionContextHelper {
    override implicit def executionContext: ExecutionContext = externalRequestExecutionPool
  }

}
