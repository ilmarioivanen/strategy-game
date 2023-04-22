package skills

import characters.Character
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

class QuickShot extends Skill(

  name = "Quickshot",
  attack = true,
  heals = false,
  negative = false,
  nerfs = false,
  mpCost = 0

) {

  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }

  def dmg(user: Character) =
    (user.baseAtkDmg * 0.2).toInt

  def effect(user: Character, target: Character): Skill =
    user.endTurn()
    user.changeSpeed(10)
    target.takeDamage(dmg(user))
    this

}
