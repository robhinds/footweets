package io.github.robhinds.wc2018.services

import io.github.robhinds.akkops.model.core.Response.Response
import io.github.robhinds.akkops.model.core.Response._
import io.github.robhinds.wc2018.model.{Stats, Update}
import io.github.robhinds.wc2018.modules.LatestUpdateModule

import scala.concurrent.Future

trait MockLatestUpdateModule extends LatestUpdateModule {
  override def latestUpdateService: LatestUpdateService = new LatestUpdateService {

    override def getLatestUpdates(paging: Int): Future[Response[Seq[Update]]] =
      Future(success(Seq(
        Update("some message about some things", "author1", Some(0)),
        Update("some message about some #WorldCup and #FRA #ENG", "author2", Some(100))
      )))

    override def addUpdate(u: Update): Unit = true

    override def getStats: Future[Response[Map[String, Stats]]] = Future(success(
      Map("England", Stats(12, 7, 75))
    ))
  }
}
