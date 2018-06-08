package io.github.robhinds.wc2018.services

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage
import com.typesafe.scalalogging.LazyLogging
import io.github.robhinds.wc2018.model.Countries.Country
import io.github.robhinds.wc2018.model.{Countries, Update}
import io.github.robhinds.wc2018.modules.LatestUpdateModule

class TweetConsumerService extends LazyLogging {
  this: LatestUpdateModule =>

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
      latestUpdateService.addUpdate(
        Update(
          content = tweet.text,
          author = tweet.user.map(u => u.name).getOrElse("UNKNOWN_AUTHOR"),
          mentionedCountries = listCountiesMentioned(tweet.text)
        )
      )
    }
  }

}
