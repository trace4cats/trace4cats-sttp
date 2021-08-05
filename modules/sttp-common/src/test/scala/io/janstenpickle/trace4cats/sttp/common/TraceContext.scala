package io.janstenpickle.trace4cats.sttp.common

import io.janstenpickle.trace4cats.base.optics.{Getter, Lens}
import io.janstenpickle.trace4cats.model.TraceHeaders
import io.janstenpickle.trace4cats.{Span, ToHeaders}

case class TraceContext[F[_]](correlationId: String, span: Span[F])

object TraceContext {
  def span[F[_]]: Lens[TraceContext[F], Span[F]] = Lens[TraceContext[F], Span[F]](_.span)(s => _.copy(span = s))
  def headers[F[_]](toHeaders: ToHeaders): Getter[TraceContext[F], TraceHeaders] =
    ctx => toHeaders.fromContext(ctx.span.context) + ("X-Correlation-ID" -> ctx.correlationId)
}
