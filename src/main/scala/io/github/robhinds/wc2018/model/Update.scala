package io.github.robhinds.wc2018.model

case class Update(
  content: String,
  author: String,
  sentimentScore: Int = 50
)
