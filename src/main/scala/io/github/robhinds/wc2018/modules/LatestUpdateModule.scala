package io.github.robhinds.wc2018.modules

import io.github.robhinds.wc2018.services.{InMemoryLatestUpdateService, LatestUpdateService}

trait LatestUpdateModule {
  def latestUpdateService: LatestUpdateService
}

trait InMemoryLatestUpdateModule extends LatestUpdateModule {
  def latestUpdateService: LatestUpdateService = InMemoryLatestUpdateService
}
