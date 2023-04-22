package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class ShadowStrike extends Skill(

  name = "Shadow Strike",
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

  def dmg(user: Character) =
    if user.currentMp == user.baseHp then
      (user.baseAtkDmg * 0.5).toInt
    else
      (user.baseAtkDmg * 0.25).toInt

  def effect(user: Character, target: Character): Skill =
    if user.hasMp then
      user.changeMp(-mpCost)
      target.takeDamage(dmg(user))
      target.changeMp(-mpCost)
    user.endTurn()
    this

}