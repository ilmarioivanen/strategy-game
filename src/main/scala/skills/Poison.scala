package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Poison extends Skill(

  name = "Poison",
  attack = true,
  heals = false,
  negative = false,
  nerfs = true,
  mpCost = 30

) {

  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }

  def dmg(user: Character) = 1

  def effect(user: Character, target: Character): Skill =
    if user.hasMp then
      user.changeMp(-mpCost)
      val missingHp = target.baseHp - target.currentMp
      target.takeDamage(dmg(user) * missingHp)
    user.endTurn()
    this

}