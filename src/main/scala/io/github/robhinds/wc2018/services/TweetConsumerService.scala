package io.github.robhinds.wc2018.services

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage
import com.typesafe.scalalogging.LazyLogging
import io.github.robhinds.wc2018.model.Countries.Country
import io.github.robhinds.wc2018.model.{Countries, Update}
import io.github.robhinds.wc2018.modules.{LatestUpdateModule, SentimentModule}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure

class TweetConsumerService extends LazyLogging {
  this: LatestUpdateModule with SentimentModule =>

  def start = {
    logger.info( "starting streaming" )
    val client = TwitterStreamingClient()
    client.filterStatuses(stall_warnings = true, tracks = Seq("Russia2018", "WorldCup", "FIFA"))(handleTweet)
  }

  def extractHashtags(s: String) = {
    val p = "#[a-zA-Z0-9]*".r
    p findAllIn s
  }

  def aboutATeam(s: String): Boolean = listCountiesMentioned(s).nonEmpty

  def listCountiesMentioned(s: String): Seq[Country] =
    (extractHashtags(s) flatMap (h => Countries.lookupHashtag(h)) toSeq).distinct

  def handleTweet: PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet => if(aboutATeam(tweet.text) && !tweet.possibly_sensitive) {
      val sentiment = sentimentService.getSentiment(tweet.text)
      for {
        s <- sentiment
        _ <-
          latestUpdateService.addUpdate(
            Update(
              content = tweet.text,
              author = tweet.user.map(u => u.name).getOrElse("UNKNOWN_AUTHOR"),
              mentionedCountries = listCountiesMentioned(tweet.text),
              sentimentScore = Some(s.score)
            )
          )
      } yield s
    }
  }

}
