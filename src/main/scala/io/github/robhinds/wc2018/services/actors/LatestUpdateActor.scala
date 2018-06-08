package io.github.robhinds.wc2018.services.actors

import akka.actor.Actor
import io.github.robhinds.wc2018.model.Update

class LatestUpdateActor extends Actor {
  import LatestUpdateActor._

  self ! Init

  def receive: Receive = {
    case Init => context become ready(Seq())
  }

  private def ready(updates: Seq[Update]): Receive = {
    case NewUpdate(u) => context become ready(u +: updates.take(19))
    case GetLatestUpdates => sender() ! updates
  }
}

object LatestUpdateActor {
  case object Init
  case class NewUpdate(u: Update)
  case object GetLatestUpdates
}
