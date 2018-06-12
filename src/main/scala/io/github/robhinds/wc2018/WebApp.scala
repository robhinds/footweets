package io.github.robhinds.wc2018

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import io.github.robhinds.wc2018.modules.{AwsSentimentModule, DummySentimentModule, InMemoryLatestUpdateModule}
import io.github.robhinds.wc2018.routes.Routing
import io.github.robhinds.wc2018.services.TweetConsumerService

import scala.concurrent.ExecutionContext
import scala.util.Try


object WebApp extends App with LazyLogging {

  private implicit val system = ActorSystem()
  protected implicit val executor: ExecutionContext = system.dispatcher
  protected implicit val materializer: ActorMaterializer = ActorMaterializer()
  val allRoutes = (new Routing() with InMemoryLatestUpdateModule).routes
  val twitterClient = new TweetConsumerService() with InMemoryLatestUpdateModule with DummySentimentModule

  logger.info("Starting Twitter connection")
  twitterClient.start

  logger.info( "starting server" )
  val port = Try(sys.env("PORT").toInt).getOrElse(8080)
  Http().bindAndHandle( logRequestResult("log",Logging.InfoLevel)( allRoutes ), "0.0.0.0", port )
  logger.info( "server started, awaiting requests.." )
}
