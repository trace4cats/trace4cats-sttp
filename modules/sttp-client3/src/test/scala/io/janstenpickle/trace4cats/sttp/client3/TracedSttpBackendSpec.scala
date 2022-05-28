package io.janstenpickle.trace4cats.sttp.client3

import cats.data.Kleisli
import cats.effect.IO
import trace4cats.Span
import trace4cats.sttp.client3.syntax._
import trace4cats.sttp.common.RunIOToId

class TracedSttpBackendSpec
    extends BaseSttpBackendTracerSpec[IO, Kleisli[IO, Span[IO], *], Span[IO]](RunIOToId, identity, _.liftTrace())
