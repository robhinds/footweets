package io.github.robhinds.wc2018.di

import io.github.robhinds.wc2018.LatestUpdatesRouting
import io.github.robhinds.wc2018.modules.InMemoryLatestUpdateModule

trait Configuration {

  val allRoutes = (new LatestUpdatesRouting() with InMemoryLatestUpdateModule).routes
}
