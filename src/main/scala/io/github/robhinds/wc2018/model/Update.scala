package io.github.robhinds.wc2018.model

import io.github.robhinds.wc2018.model.Countries.Country

case class Update(
  content: String,
  author: String,
  sentimentScore: Option[Int] = None,
  mentionedCountries: Seq[Country] = Seq()
)
