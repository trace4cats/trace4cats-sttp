package trace4cats.sttp.tapir

import cats.data.Kleisli
import cats.effect.IO
import trace4cats.Span
import trace4cats.sttp.common.RunIOToId

class TracedServerEndpointSpec
    extends BaseServerEndpointTracerSpec[IO](
      RunIOToId,
      new Endpoints[IO, Kleisli[IO, Span[IO], *]].tracedEndpoints(_),
      checkMkContextErrors = false
    )
