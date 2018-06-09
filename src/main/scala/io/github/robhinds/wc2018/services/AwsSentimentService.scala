package io.github.robhinds.wc2018.services

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder
import com.amazonaws.services.comprehend.model.DetectSentimentRequest

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object AwsSentimentService extends SentimentService {

  override def getSentiment(s: String): Future[String] = {
    val awsCreds = DefaultAWSCredentialsProviderChain.getInstance
    val comprehendClient = AmazonComprehendClientBuilder.standard.withCredentials(awsCreds).withRegion("region").build

    Future {
      System.out.println("Calling DetectSentiment")
      val detectSentimentRequest = new DetectSentimentRequest().withText(s).withLanguageCode("en")
      val detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest)
      System.out.println(detectSentimentResult)
      System.out.println("Done")
      detectSentimentResult.getSentiment
    }
  }

}
