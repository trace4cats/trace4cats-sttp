package trace4cats.sttp.client3

import sttp.client3.Response
import trace4cats.model.{AttributeValue, SemanticAttributeKeys}

object SttpResponse {
  def toAttributes[T](res: Response[T]): List[(String, AttributeValue)] =
    List(
      SemanticAttributeKeys.httpStatusCode -> res.code.code,
      SemanticAttributeKeys.httpStatusMessage -> res.statusText
    )
}
