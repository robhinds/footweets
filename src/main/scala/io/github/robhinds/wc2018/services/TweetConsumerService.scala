package io.github.robhinds.wc2018.services

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage
import io.github.robhinds.wc2018.model.Update

class TweetConsumerService {

  def start = {
    val client = TwitterStreamingClient()
    client.sampleStatuses(stall_warnings = true)(handleTweet)
  }

  def extractHashtags(s: String) = {
    val p = "#[a-zA-Z0-9]*".r
    p findAllIn s
  }

  def filterRelevant(s: String): Boolean = {
    extractHashtags(s) exists (h => h.toLowerCase.contains("russia2018") || h.toLowerCase.contains("worldcup"))
  }

  def handleTweet: PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet => if(filterRelevant(tweet.text)) {
      println (tweet.text)
      InMemoryLatestUpdateService.addUpdate(Update(tweet.user.map(u => u.name).getOrElse("UNKNOWN_AUTHOR"), tweet.text))
    }
  }

}
