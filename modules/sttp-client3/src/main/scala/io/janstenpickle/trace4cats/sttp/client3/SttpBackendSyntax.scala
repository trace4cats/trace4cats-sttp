package io.janstenpickle.trace4cats.sttp.client3

import cats.effect.kernel.{Async, MonadCancelThrow}
import io.janstenpickle.trace4cats.base.context.Provide
import io.janstenpickle.trace4cats.base.optics.{Getter, Lens}
import io.janstenpickle.trace4cats.model.{AttributeValue, TraceHeaders}
import io.janstenpickle.trace4cats.{Span, ToHeaders}
import sttp.client3.{Response, SttpBackend}

trait SttpBackendSyntax {

  implicit class TracedSttpBackendSyntax[F[_], +P](backend: SttpBackend[F, P]) {
    def liftTrace[G[_]](
      toHeaders: ToHeaders = ToHeaders.standard,
      spanNamer: SttpSpanNamer = SttpSpanNamer.methodWithPath,
      attributesFromResponse: Getter[Response[Unit], Map[String, AttributeValue]] = Getter(_ => Map.empty)
    )(implicit P: Provide[F, G, Span[F]], F: MonadCancelThrow[F], G: Async[G]): SttpBackend[G, P] =
      new SttpBackendTracer[F, G, P, Span[F]](
        backend,
        Lens.id,
        Getter((toHeaders.fromContext _).compose(_.context)),
        spanNamer,
        attributesFromResponse
      )

    def liftTraceContext[G[_], Ctx](
      spanLens: Lens[Ctx, Span[F]],
      headersGetter: Getter[Ctx, TraceHeaders],
      spanNamer: SttpSpanNamer = SttpSpanNamer.methodWithPath,
      attributesFromResponse: Getter[Response[Unit], Map[String, AttributeValue]] = Getter(_ => Map.empty)
    )(implicit P: Provide[F, G, Ctx], F: MonadCancelThrow[F], G: Async[G]): SttpBackend[G, P] =
      new SttpBackendTracer[F, G, P, Ctx](backend, spanLens, headersGetter, spanNamer, attributesFromResponse)
  }

}
