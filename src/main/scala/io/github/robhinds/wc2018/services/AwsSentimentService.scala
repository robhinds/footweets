package io.github.robhinds.wc2018.services

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder
import com.amazonaws.services.comprehend.model.{DetectSentimentRequest, DetectSentimentResult}
import io.github.robhinds.wc2018.model.Sentiment

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object AwsSentimentService extends SentimentService {

  override def getSentiment(s: String): Future[Sentiment] = {
    val awsCreds = DefaultAWSCredentialsProviderChain.getInstance
    val comprehendClient = AmazonComprehendClientBuilder.standard.withCredentials(awsCreds).withRegion("eu-west-1").build
    Future {
      val req = new DetectSentimentRequest().withText(s).withLanguageCode("en")
      val result = comprehendClient.detectSentiment(req)
      println(result)
      Sentiment(result.getSentiment, getSentimentScore(result))
    }
  }

  def getSentimentScore(r: DetectSentimentResult): Int = r.getSentiment match {
    case "POSITIVE" => (r.getSentimentScore.getPositive * 100).toInt
    case "NEGATIVE" => (r.getSentimentScore.getNegative * -100).toInt
    case _ => 0
  }

}
