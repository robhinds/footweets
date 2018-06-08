package io.github.robhinds.wc2018.model

object Countries {

  def lookupHashtag(hashtag: String): Option[Country] = hashtag.toUpperCase match {
    case "#ENG" | "#ENGLAND" => Some(England())
    case "#BRA" | "#BRAZIL" => Some(Brazil())
    case "#GER" | "#GERMANY" => Some(Germany())
    case "#ARG" | "#ARGENTINA" => Some(Argentina())
    case "#AUS" | "#AUSTRALIA" => Some(Australia())
    case "#BEL" | "#BELGIUM" => Some(Belgium())
    case "#SPA" | "#SPAIN" => Some(Spain())
    case "#FRA" | "#FRANCE" => Some(France())
    case "#POR" | "#PORTUGAL" => Some(Portugal())
    case "#COL" | "#COLOMBIA" => Some(Colombia())
    case "#COS" | "#COSTARICA" => Some(CostaRica())
    case "#CRO" | "#CROATIA" => Some(Croatia())
    case "#DEN" | "#DENMARK" => Some(Denmark())
    case "#EGY" | "#EGYPT" => Some(Egypt())
    case "#ICE" | "#ICELAND" => Some(Iceland())
    case "#IRA" | "#IRAN" => Some(Iran())
    case "#JAP" | "#JAPAN" => Some(Japan())
    case "#MEX" | "#MEXICO" => Some(Mexico())
    case "#MOR" | "#MOROCCO" => Some(Morocco())
    case "#NIG" | "#NIGERIA" => Some(Nigeria())
    case "#PAN" | "#PANAMA" => Some(Panama())
    case "#PER" | "#PERU" => Some(Peru())
    case "#POL" | "#POLAND" => Some(Poland())
    case "#SAU" | "#SAUDIARABIA" => Some(SaudiArabia())
    case "#SEN" | "#SENEGAL" => Some(Senegal())
    case "#SER" | "#SERBIA" => Some(Serbia())
    case "#SKO" | "#SOUTHKOREA" => Some(SouthKorea())
    case "#SWE" | "#SWEDEN" => Some(Sweden())
    case "#SWI" | "#SWITZERLAND" => Some(Switzerland())
    case "#TUN" | "#TUNISIA" => Some(Tunisia())
    case "#URU" | "#URUGUAY" => Some(Uruguay())
    case _ => None
  }

  sealed trait Country{
    val name:String
  }
  case class Brazil(name:String = "Brazil") extends Country
  case class Germany(name:String = "Germany") extends Country
  case class Argentina(name:String = "Argentina") extends Country
  case class Australia(name:String = "Australia") extends Country
  case class Belgium(name:String = "Belgium") extends Country
  case class Colombia(name:String = "Colombia") extends Country
  case class CostaRica(name:String = "CostaRica") extends Country
  case class Croatia(name:String = "Croatia") extends Country
  case class Denmark(name:String = "Denmark") extends Country
  case class Egypt(name:String = "Egypt") extends Country
  case class England(name:String = "England") extends Country
  case class France(name:String = "France") extends Country
  case class Iceland(name:String = "Iceland") extends Country
  case class Iran(name:String = "Iran") extends Country
  case class Japan(name:String = "Japan") extends Country
  case class Mexico(name:String = "Mexico") extends Country
  case class Morocco(name:String = "Morocco") extends Country
  case class Nigeria(name:String = "Nigeria") extends Country
  case class Panama(name:String = "Panama") extends Country
  case class Peru(name:String = "Peru") extends Country
  case class Poland(name:String = "Poland") extends Country
  case class Portugal(name:String = "Portugal") extends Country
  case class SaudiArabia(name:String = "SaudiArabia") extends Country
  case class Senegal(name:String = "Senegal") extends Country
  case class Serbia(name:String = "Serbia") extends Country
  case class SouthKorea(name:String = "SouthKorea") extends Country
  case class Spain(name:String = "Spain") extends Country
  case class Sweden(name:String = "Sweden") extends Country
  case class Switzerland(name:String = "Switzerland") extends Country
  case class Tunisia(name:String = "Tunisia") extends Country
  case class Uruguay(name:String = "Uruguay") extends Country
}