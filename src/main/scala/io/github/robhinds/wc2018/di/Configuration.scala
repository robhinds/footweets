package io.github.robhinds.wc2018.di

import io.github.robhinds.wc2018.modules.InMemoryLatestUpdateModule
import io.github.robhinds.wc2018.routes.LatestUpdatesRouting

trait Configuration {

  val allRoutes = (new LatestUpdatesRouting() with InMemoryLatestUpdateModule).routes
}
