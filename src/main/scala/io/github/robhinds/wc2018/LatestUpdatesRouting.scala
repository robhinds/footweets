package io.github.robhinds.wc2018

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import io.github.robhinds.akkops.routing.directives.Directives._
import io.github.robhinds.akkops.routing.DefaultResponseHandler._
import io.circe.generic.auto._
import io.github.robhinds.wc2018.modules.LatestUpdateModule

class LatestUpdatesRouting {
  this: LatestUpdateModule =>

  val routes: Route =
    getPath("latest") {
      respond(latestUpdateService.getLatestUpdates())
    }

}
