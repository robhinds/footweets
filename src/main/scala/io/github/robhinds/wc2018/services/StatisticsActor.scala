package io.github.robhinds.wc2018.services

import akka.actor.Actor
import io.github.robhinds.wc2018.model.Countries.Country
import io.github.robhinds.wc2018.model.Stats

class StatisticsActor extends Actor {
  import StatisticsActor._

  self ! Init

  def receive: Receive = {
    case Init => context become ready(Map[String, Stats]())
  }

  private def ready(stats: Map[String, Stats]): Receive = {
    case UpdateStats(c, i) =>
      val newStats = c.foldLeft(stats) { (a, country) =>
        a + (country.name -> a.getOrElse(country.name, Stats()))
      }
      context become ready(newStats)
    case GetAllStats => sender() ! stats
  }
}

object StatisticsActor {
  case object Init
  case class UpdateStats(c: Seq[Country], i: Option[Int] = None)
  case object GetAllStats
}