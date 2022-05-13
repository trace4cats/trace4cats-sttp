package io.janstenpickle.trace4cats.sttp.common

import cats.{~>, Id}
import cats.effect.IO
import io.janstenpickle.trace4cats.sttp.common.CommonInstances._

object RunIOToId extends IO ~> Id {
  def apply[A](fa: IO[A]): Id[A] = fa.unsafeRunSync()
}
