package trace4cats.sttp

import sttp.tapir.Endpoint
import trace4cats.model.SpanStatus

package object tapir {
  type TapirSpanNamer[I] = (Endpoint[_, I, _, _, _], I) => String
  type TapirInputSpanNamer[I] = I => String
  type TapirStatusMapping[E] = E => SpanStatus
}
