package io.github.robhinds.wc2018

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage
import io.github.robhinds.wc2018.di.Configuration

import scala.concurrent.ExecutionContext


object WebApp extends App with LazyLogging with Configuration {

  private implicit val system = ActorSystem()
  protected implicit val executor: ExecutionContext = system.dispatcher
  protected implicit val materializer: ActorMaterializer = ActorMaterializer()

  logger.info( "starting firehose" )


  logger.info( "starting server" )
  Http().bindAndHandle( logRequestResult("log",Logging.InfoLevel)( allRoutes ), "0.0.0.0", 8080 )
  logger.info( "server started, awaiting requests.." )
}
