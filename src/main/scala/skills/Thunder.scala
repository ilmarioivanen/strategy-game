package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Thunder extends Skill(

  name = "Thunder",
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
    (user.baseMgcDmg * 0.5).toInt

  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      target.takeDamage(dmg(user))
    user.endTurn()
    (this, user, target)

}