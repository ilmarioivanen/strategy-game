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

    // Since this just casts the skill on target by target,
    // the target's turn is used and the target also needs the mana for casting
    val eruption = new Explosion
    val mana = eruption.mpCost
    target.changeMp(mana)
    eruption.effect(target, target)

    // Placeholder visual
    val visual = new Circle() {
      fill = Red
    }
    val description = s"The volcano erupted! ${target.name} was affected!"

    (visual, description)
}
