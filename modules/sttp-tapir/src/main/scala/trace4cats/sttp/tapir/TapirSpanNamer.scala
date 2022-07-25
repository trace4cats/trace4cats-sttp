package trace4cats.sttp.tapir

object TapirSpanNamer {
  def method[I]: TapirSpanNamer[I] = (ep, _) => ep.method.map(_.toString()).getOrElse("*")
  def pathTemplate[I]: TapirSpanNamer[I] = (ep, _) => ep.showPathTemplate(showQueryParam = None)
  def methodWithPathTemplate[I]: TapirSpanNamer[I] = (ep, i) => s"${method(ep, i)} ${pathTemplate(ep, i)}"
}
