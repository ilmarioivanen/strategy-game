package stages

import characters.Character
import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Circle
import skills.Explosion

class Volcano extends Stage("Volcano") {

  override val background = Background(Array(new BackgroundFill((DarkOrange), CornerRadii.Empty, Insets.Empty)))

  // Volcano erupts
  def effect(target: Character) =

    val eruption = new Explosion
    eruption.effect(target, target)

    // Placeholder visual
    val visual = new Circle() {
      fill = Red
    }
    val description = s"The volcano erupted! ${target.name} was affected!"

    (visual, description)
}
