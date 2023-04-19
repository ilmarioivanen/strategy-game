package stages

import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*

class Battleground extends Stage("Battleground") {

  // Just a one colored background for now
  // BackgroundImages could be used for more interesting backgrounds

  override val background = Background(Array(new BackgroundFill((LawnGreen), CornerRadii.Empty, Insets.Empty)))
}

