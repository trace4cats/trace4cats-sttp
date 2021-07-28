package io.janstenpickle.trace4cats.sttp.common

import cats.effect.unsafe.IORuntime

trait CommonInstances {
  implicit val runtime: IORuntime = IORuntime.global
}

object CommonInstances extends CommonInstances
