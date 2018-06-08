package io.github.robhinds.wc2018.services

import io.github.robhinds.wc2018.model.Countries.{France, Spain}
import org.scalatest.{FlatSpec, Matchers}

class TweetConsumerServiceSpec extends FlatSpec with Matchers {

  "relevant messages" should "be filtered" in {
    val c = new TweetConsumerService() with MockLatestUpdateModule
    c.aboutATeam("some tweet about #eng and the football this year") shouldBe true
    c.aboutATeam("#brazil and the football this year") shouldBe true
    c.aboutATeam("#FRANCE and the football this year") shouldBe true
    c.aboutATeam("#FRA and #SPA the football this year") shouldBe true
    c.aboutATeam("another one #BRA, even punctuation doesnt matter") shouldBe true
  }

  "extracting hashtags" should "work ok" in {
    val c = new TweetConsumerService() with MockLatestUpdateModule
    c.extractHashtags("some tweet about #russia2018 and the football this year").toList shouldBe List("#russia2018")
    c.extractHashtags("case insensitive #YOLO tweet").toList shouldBe List("#YOLO")
  }

  "list countries mentioned" should "capture all mentioned" in {
    val c = new TweetConsumerService() with MockLatestUpdateModule
    c.listCountiesMentioned("#FRA and #SPA the football this year") shouldBe Seq(France(), Spain())
  }

  "list countries mentioned" should "return unique values" in {
    val c = new TweetConsumerService() with MockLatestUpdateModule
    c.listCountiesMentioned("#FRA #FRA #FRA #FRA !!") shouldBe Seq(France())
  }
}
