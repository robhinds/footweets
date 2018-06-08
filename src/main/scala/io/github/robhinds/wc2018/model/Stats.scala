package io.github.robhinds.wc2018.model

case class Stats(
  count: Int = 1,
  sentimentCount: Int = 0,
  averageSentiment: Int = 50
) {
  def update(score: Option[Int]) = {
    score match {
      case Some(s) => this.copy(
        count = this.count + 1,
        averageSentiment = (this.averageSentiment + s)/(this.sentimentCount+1),
        sentimentCount = this.sentimentCount + 1
      )
      case _ => this.copy(count = this.count + 1)
    }
  }
}
