package io.github.robhinds.wc2018.services

import io.github.robhinds.akkops.model.core.Response.Response
import io.github.robhinds.wc2018.model.Update

import scala.concurrent.Future

trait LatestUpdateService {
  def getLatestUpdates(paging: Int = 10): Future[Response[Seq[Update]]]
  def addUpdate(u: Update): Unit
}
