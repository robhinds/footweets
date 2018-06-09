package io.github.robhinds.wc2018.services

import scala.concurrent.Future

trait SentimentService {

  def getSentiment(s: String): Future[String]

}
