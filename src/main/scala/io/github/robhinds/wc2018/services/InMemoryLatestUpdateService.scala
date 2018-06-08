package io.github.robhinds.wc2018.services
import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask
import io.github.robhinds.akkops.model.core.Response.Response
import io.github.robhinds.akkops.model.core.Response._
import io.github.robhinds.wc2018.model.Update
import io.github.robhinds.wc2018.services.LatestUpdateActor.{GetLatestUpdates, NewUpdate}

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object InMemoryLatestUpdateService extends LatestUpdateService {

  private implicit val timeout = Timeout(15 seconds)
  private val system = ActorSystem("LastUpdateActors")
  private val latestUpdatesActor = system.actorOf(Props[LatestUpdateActor], name = "latest-updates-actor")

  override def getLatestUpdates(paging: Int): Future[Response[Seq[Update]]] =
    (latestUpdatesActor ? GetLatestUpdates()).mapTo[Seq[Update]] map success

  override def addUpdate(u: Update): Unit = latestUpdatesActor ! NewUpdate(u)

}
