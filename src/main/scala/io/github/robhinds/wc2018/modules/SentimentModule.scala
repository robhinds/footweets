package io.github.robhinds.wc2018.modules

import io.github.robhinds.wc2018.services.{AwsSentimentService, SentimentService}

trait SentimentModule {
  def sentimentService: SentimentService
}

trait AwsSentimentModule extends SentimentModule {
  def sentimentService: SentimentService = AwsSentimentService
}
