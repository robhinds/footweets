package io.github.robhinds.wc2018.services

import org.scalatest.{FlatSpec, Matchers}

class TweetConsumerServiceSpec extends FlatSpec with Matchers {

  "relevant messages" should "be filtered" in {
    val c = new TweetConsumerService()
    c.filterRelevant("some tweet about #russia2018 and the football this year") shouldBe true
    c.filterRelevant("#Russia2018 and the football this year") shouldBe true
    c.filterRelevant("#WorldCUP and the football this year") shouldBe true
    c.filterRelevant("#worldcup and the football this year") shouldBe true
    c.filterRelevant("another one #WorldCUP, even punctuation doesnt matter") shouldBe true
  }

  "extracting hashtags" should "work ok" in {
    val c = new TweetConsumerService()
    c.extractHashtags("some tweet about #russia2018 and the football this year").toList shouldBe List("#russia2018")
    c.extractHashtags("case insensitive #YOLO tweet").toList shouldBe List("#YOLO")
  }
}
