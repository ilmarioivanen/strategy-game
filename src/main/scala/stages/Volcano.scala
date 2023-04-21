package stages

import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*

class Volcano extends Stage("Volcano") {

  override val background = Background(Array(new BackgroundFill((DarkOrange), CornerRadii.Empty, Insets.Empty)))
}
