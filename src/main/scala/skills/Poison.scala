package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Poison extends Skill(

  name = "Poison",
  attack = true,
  heals = false,
  negative = false,
  nerfs = false,
  mpCost = 30

) {

  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }

  def dmg(user: Character) =
    // Not the correct damage number but something for the ai
    30

  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      val missingHp = target.baseHp - target.currentHp
      target.takeDamage(missingHp)
    user.endTurn()
    (this, user, target)

}