package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Dodge extends Skill(

  name = "Dodge",
  attack = true,
  heals = false,
  negative = false,
  nerfs = false,
  mpCost = 0

) {

  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }

  def dmg(user: Character) =
    (user.baseAtkDmg * 0.05).toInt

  def effect(user: Character, target: Character): (Skill, Character, Character) =
    user.changeMp(20)
    target.takeDamage(dmg(user))
    user.endTurn()
    (this, user, target)

}
