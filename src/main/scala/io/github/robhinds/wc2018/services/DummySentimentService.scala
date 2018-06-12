package io.github.robhinds.wc2018.services

import io.github.robhinds.wc2018.model.Sentiment

import scala.concurrent.Future
import scala.util.Random

object DummySentimentService extends SentimentService {

  override def getSentiment(s: String): Future[Sentiment] = {
    Future.successful(Sentiment("", Random.nextInt(200) - 100))
  }
}
