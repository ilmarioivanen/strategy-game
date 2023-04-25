package stages

import characters.*
import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Circle
import skills.Cripple

class Desert extends Stage("Desert") {

  override val background = Background(Array(new BackgroundFill((SandyBrown), CornerRadii.Empty, Insets.Empty)))
  
  // Desert slows and deals damage
  def effect(target: Character) =

    // Since this just casts the skill on target by target,
    // the target's turn is used and the target also needs the mana for casting
    val slow = new Cripple
    val mana = slow.mpCost
    target.changeMp(mana)
    slow.effect(target, target)

    // Placeholder visual
    val visual = new Circle() {
      fill = Brown
    }
    val description = s"The sandstorm has slowed down ${target.name}."

    (visual, description)
}
