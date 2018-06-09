package io.github.robhinds.wc2018.services

import io.github.robhinds.wc2018.modules.SentimentModule

import scala.concurrent.Future

trait MockSentimentModule extends SentimentModule {
  override def sentimentService: SentimentService = new SentimentService {
    override def getSentiment(s: String): Future[String] = Future.successful(
      if (s == "good") "POSITIVE"
      else if (s=="bad") "NEGATIVE"
      else "NEUTRAL"
    )
  }
}
