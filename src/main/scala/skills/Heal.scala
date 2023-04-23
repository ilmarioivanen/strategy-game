package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Heal extends Skill(

  name = "Heal",
  attack = false,
  heals = true,
  negative = false,
  nerfs = false,
  mpCost = 50

) {

  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }

  def dmg(user: Character) = 0

  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      target.heal(50)
    user.endTurn()
    (this, user, target)

}
