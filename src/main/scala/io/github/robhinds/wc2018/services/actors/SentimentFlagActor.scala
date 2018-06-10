package io.github.robhinds.wc2018.services.actors

import akka.actor.Actor

class SentimentFlagActor extends Actor {
  import SentimentFlagActor._

  self ! Init

  def receive: Receive = {
    case Init => context become ready(true)
  }

  private def ready(on: Boolean): Receive = {
    case SwitchFlag => context become ready(!on)
    case GetSentimentFlag => sender() ! on
  }

}

object SentimentFlagActor {
  case object Init
  case object SwitchFlag
  case object GetSentimentFlag
}
