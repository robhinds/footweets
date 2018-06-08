package io.github.robhinds.wc2018.services

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask
import io.github.robhinds.akkops.model.core.Response.Response
import io.github.robhinds.akkops.model.core.Response._
import io.github.robhinds.wc2018.model.{Stats, Update}
import io.github.robhinds.wc2018.services.StatisticsActor.{GetAllStats, UpdateStats}
import io.github.robhinds.wc2018.services.actors.{LatestUpdateActor, StatisticsActor}

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object InMemoryLatestUpdateService extends LatestUpdateService {

  private implicit val timeout = Timeout(15 seconds)
  private val system = ActorSystem("LastUpdateActors")
  private val latestUpdatesActor = system.actorOf(Props[LatestUpdateActor], name = "latest-updates-actor")
  private val statsActor = system.actorOf(Props[StatisticsActor], name = "stats-actor")

  override def getLatestUpdates(paging: Int): Future[Response[Seq[Update]]] =
    (latestUpdatesActor ? GetLatestUpdates()).mapTo[Seq[Update]] map success

  override def addUpdate(u: Update): Unit = {
    latestUpdatesActor ! NewUpdate(u)
    statsActor ! UpdateStats( u.mentionedCountries, u.sentimentScore)
  }

  override def getStats: Future[Response[Map[String, Stats]]] =
    (statsActor ? GetAllStats).mapTo[Map[String, Stats]] map success
}
