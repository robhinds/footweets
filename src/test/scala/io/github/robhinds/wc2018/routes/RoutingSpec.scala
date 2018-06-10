package io.github.robhinds.wc2018.routes

import org.scalatest.{FlatSpec, Matchers}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import io.github.robhinds.wc2018.services.MockLatestUpdateModule

class RoutingSpec extends FlatSpec with Matchers with ScalatestRouteTest {

  "calling latest" should "return results" in {
  val r = new Routing() with MockLatestUpdateModule
    Get("/latest") ~> r.routes ~> check {
      responseAs[String] shouldEqual "{\n  \"status\" : \"200 OK\",\n  \"data\" : [\n    {\n      \"content\" : \"some message about some things\",\n      \"author\" : \"author1\",\n      \"sentimentScore\" : 0,\n      \"mentionedCountries\" : [\n      ]\n    },\n    {\n      \"content\" : \"some message about some #WorldCup and #FRA #ENG\",\n      \"author\" : \"author2\",\n      \"sentimentScore\" : 100,\n      \"mentionedCountries\" : [\n      ]\n    }\n  ]\n}"
    }
  }


  "calling stats" should "return serialised stats" in {
    val r = new Routing() with MockLatestUpdateModule
    Get("/stats") ~> r.routes ~> check {
      responseAs[String] shouldEqual "{\n  \"status\" : \"200 OK\",\n  \"data\" : {\n    \"England\" : {\n      \"count\" : 12,\n      \"sentimentCount\" : 7,\n      \"averageSentiment\" : 75\n    }\n  }\n}"
    }
  }

}
