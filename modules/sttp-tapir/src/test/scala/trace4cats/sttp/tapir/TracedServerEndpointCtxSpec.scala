package trace4cats.sttp.tapir

import cats.data.Kleisli
import cats.effect.IO
import trace4cats.sttp.common.{RunIOToId, TraceContext}
import trace4cats.sttp.tapir.Instances._

class TracedServerEndpointCtxSpec
    extends BaseServerEndpointTracerSpec[IO](
      RunIOToId,
      new Endpoints[IO, Kleisli[IO, TraceContext[IO], *]].tracedContextEndpoints(_),
      checkMkContextErrors = true
    )
