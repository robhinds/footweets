package io.github.robhinds.wc2018.services

import io.github.robhinds.akkops.model.core.Response.Response
import io.github.robhinds.wc2018.model.Countries.Country
import io.github.robhinds.wc2018.model.{Stats, Update}

import scala.concurrent.Future

trait LatestUpdateService {
  def getLatestUpdates: Future[Response[Seq[Update]]]
  def addUpdate(u: Update): Future[Unit]
  def getStats: Future[Response[Map[String, Stats]]]
}
