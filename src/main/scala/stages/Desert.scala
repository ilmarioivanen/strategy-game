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
    
    val slow = new Cripple
    slow.effect(target, target)

    // Placeholder visual
    val visual = new Circle() {
      fill = Brown
    }
    val description = s"The sandstorm has slowed down ${target.name}."

    (visual, description)
}
