package trace4cats.sttp.tapir

import cats.Show
import cats.syntax.show._
import sttp.model.StatusCode
import trace4cats.model.SpanStatus
import trace4cats.optics.Getter
import trace4cats.sttp.common.SttpStatusMapping.statusToSpanStatus

object TapirStatusMapping {
  def errorShowToInternal[E: Show]: TapirStatusMapping[E] = e => SpanStatus.Internal(e.show)
  def errorMessageToInternal[E <: Throwable]: TapirStatusMapping[E] = e => SpanStatus.Internal(e.getMessage)
  def errorStringToInternal[E]: TapirStatusMapping[E] = e => SpanStatus.Internal(e.toString)

  def errorShowToSpanStatus[E: Show](statusCodeGetter: Getter[E, StatusCode]): TapirStatusMapping[E] =
    e => statusToSpanStatus(e.show, statusCodeGetter.get(e))
  def errorMessageToSpanStatus[E <: Throwable](statusCodeGetter: Getter[E, StatusCode]): TapirStatusMapping[E] =
    e => statusToSpanStatus(e.getMessage, statusCodeGetter.get(e))
  def errorStringToSpanStatus[E](statusCodeGetter: Getter[E, StatusCode]): TapirStatusMapping[E] =
    e => statusToSpanStatus(e.toString, statusCodeGetter.get(e))
}
