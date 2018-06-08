package io.github.robhinds.wc2018.model

object Countries {

  def lookupHashtag(hashtag: String): Option[Country] = hashtag.toUpperCase match {
    case "#ENG" | "#ENGLAND" => Some(England)
    case _ => None
  }

  sealed trait Country
  case object Brazil extends Country
  case object Germany extends Country
  case object Argentina extends Country
  case object Australia extends Country
  case object Belgium extends Country
  case object Colombia extends Country
  case object CostaRica extends Country
  case object Croatia extends Country
  case object Denmark extends Country
  case object Egypt extends Country
  case object England extends Country
  case object France extends Country
  case object Iceland extends Country
  case object Iran extends Country
  case object Japan extends Country
  case object Mexico extends Country
  case object Morocco extends Country
  case object Nigeria extends Country
  case object Panama extends Country
  case object Peru extends Country
  case object Poland extends Country
  case object Portugal extends Country
  case object SaudiArabia extends Country
  case object Senegal extends Country
  case object Serbia extends Country
  case object SouthKorea extends Country
  case object Spain extends Country
  case object Sweden extends Country
  case object Switzerland extends Country
  case object Tunisia extends Country
  case object Uruguay extends Country
}