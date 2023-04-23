package stages

import characters.*
import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Circle


class Battleground extends Stage("Battleground") {

  // Just a one colored background for now
  // BackgroundImages could be used for more interesting backgrounds

  override val background = Background(Array(new BackgroundFill((Green), CornerRadii.Empty, Insets.Empty)))
  
  // Battleground has no special effect
  def effect(target: Character) =

    // Placeholder visual
    val visual = new Circle() {
      fill = Green
    }
    val description = "Wind blows on the battlefield."

    (visual, description)
}

