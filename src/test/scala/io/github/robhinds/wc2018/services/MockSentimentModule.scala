package io.github.robhinds.wc2018.services

import io.github.robhinds.wc2018.model.Sentiment
import io.github.robhinds.wc2018.modules.SentimentModule

import scala.concurrent.Future

trait MockSentimentModule extends SentimentModule {
  override def sentimentService: SentimentService = new SentimentService {
    override def getSentiment(s: String): Future[Sentiment] = Future.successful(
      if (s == "good") Sentiment("POSITIVE", 78)
      else if (s=="bad") Sentiment("NEGATIVE", -69)
      else Sentiment("NEUTRAL", 0)
    )
  }
}
