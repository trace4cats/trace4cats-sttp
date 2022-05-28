package trace4cats.sttp

import sttp.tapir.Endpoint
import trace4cats.SpanName
import trace4cats.model.SpanStatus

package object tapir {
  type TapirSpanNamer[I] = (Endpoint[_, I, _, _, _], I) => SpanName
  type TapirInputSpanNamer[I] = I => SpanName
  type TapirStatusMapping[E] = E => SpanStatus
}
