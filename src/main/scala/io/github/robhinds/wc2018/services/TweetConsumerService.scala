package io.github.robhinds.wc2018.services

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage
import com.typesafe.scalalogging.LazyLogging
import io.github.robhinds.wc2018.model.{Countries, Update}

class TweetConsumerService extends LazyLogging {

  def start = {
    logger.info( "starting firehose" )
    val client = TwitterStreamingClient()
    client.filterStatuses(stall_warnings = true, tracks = Seq("Russia2018", "WorldCup", "FIFA"))(handleTweet)
  }

  def extractHashtags(s: String) = {
    val p = "#[a-zA-Z0-9]*".r
    p findAllIn s
  }

  def aboutATeam(s: String): Boolean = {
    extractHashtags(s) exists (h => Countries.lookupHashtag(h).isDefined)
  }

  def handleTweet: PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet => if(aboutATeam(tweet.text) && !tweet.possibly_sensitive) {
      println (tweet.text)
      InMemoryLatestUpdateService.addUpdate(Update(tweet.user.map(u => u.name).getOrElse("UNKNOWN_AUTHOR"), tweet.text))
    }
  }

}
