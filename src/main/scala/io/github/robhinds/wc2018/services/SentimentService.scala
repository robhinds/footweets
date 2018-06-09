package io.github.robhinds.wc2018.services

import io.github.robhinds.wc2018.model.Sentiment

import scala.concurrent.Future

trait SentimentService {

  def getSentiment(s: String): Future[Sentiment]

}
