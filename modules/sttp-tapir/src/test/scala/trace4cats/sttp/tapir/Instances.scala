package trace4cats.sttp.tapir

import cats.data.Kleisli
import cats.effect.IO
import trace4cats.Span
import trace4cats.context.Local
import trace4cats.sttp.common.{CommonInstances, TraceContext}

object Instances extends CommonInstances {
  implicit val localSpan: Local[Kleisli[IO, TraceContext[IO], *], Span[IO]] =
    Local[Kleisli[IO, TraceContext[IO], *], TraceContext[IO]].focus(TraceContext.span[IO])
}
