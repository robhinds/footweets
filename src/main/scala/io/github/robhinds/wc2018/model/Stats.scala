package io.github.robhinds.wc2018.model

case class Stats(
  count: Int = 0,
  sentimentCount: Int = 0,
  averageSentiment: Int = 0
) {
  def update(score: Option[Int]) = {
    score match {
      case Some(s) => this.copy(
        count = this.count + 1,
        averageSentiment = ((this.averageSentiment * this.sentimentCount) + s)/(this.sentimentCount+1),
        sentimentCount = this.sentimentCount + 1
      )
      case _ => this.copy(count = this.count + 1)
    }
  }
}
