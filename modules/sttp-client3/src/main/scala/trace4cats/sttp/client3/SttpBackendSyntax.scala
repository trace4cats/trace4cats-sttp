package trace4cats.sttp.client3

import cats.effect.kernel.{Async, MonadCancelThrow}
import sttp.client3.{Response, SttpBackend}
import sttp.model.HeaderNames
import trace4cats.context.Provide
import trace4cats.model.{AttributeValue, TraceHeaders}
import trace4cats.optics.{Getter, Lens}
import trace4cats.{Span, ToHeaders}

trait SttpBackendSyntax {

  implicit class TracedSttpBackendSyntax[F[_], +P](backend: SttpBackend[F, P]) {
    def liftTrace[G[_]](
      toHeaders: ToHeaders = ToHeaders.standard,
      spanNamer: SttpSpanNamer = SttpSpanNamer.methodWithPath,
      dropHeadersWhen: String => Boolean = HeaderNames.isSensitive,
      responseAttributesGetter: Getter[Response[_], Map[String, AttributeValue]] = Getter(_ => Map.empty)
    )(implicit P: Provide[F, G, Span[F]], F: MonadCancelThrow[F], G: Async[G]): SttpBackend[G, P] =
      new SttpBackendTracer[F, G, P, Span[F]](
        backend,
        Lens.id,
        Getter((toHeaders.fromContext _).compose(_.context)),
        spanNamer,
        dropHeadersWhen,
        responseAttributesGetter
      )

    def liftTraceContext[G[_], Ctx](
      spanLens: Lens[Ctx, Span[F]],
      headersGetter: Getter[Ctx, TraceHeaders],
      spanNamer: SttpSpanNamer = SttpSpanNamer.methodWithPath,
      dropHeadersWhen: String => Boolean = HeaderNames.isSensitive,
      responseAttributesGetter: Getter[Response[_], Map[String, AttributeValue]] = Getter(_ => Map.empty)
    )(implicit P: Provide[F, G, Ctx], F: MonadCancelThrow[F], G: Async[G]): SttpBackend[G, P] =
      new SttpBackendTracer[F, G, P, Ctx](
        backend,
        spanLens,
        headersGetter,
        spanNamer,
        dropHeadersWhen,
        responseAttributesGetter
      )
  }

}
