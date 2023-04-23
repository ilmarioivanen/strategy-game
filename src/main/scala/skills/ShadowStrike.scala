package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class ShadowStrike extends Skill(

  name = "ShadowStrike",
  attack = true,
  heals = false,
  negative = false,
  nerfs = true,
  mpCost = 30

) {

  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }

  def dmg(user: Character) =
    if user.currentMp == user.baseHp then
      (user.baseAtkDmg * 0.5).toInt
    else
      (user.baseAtkDmg * 0.25).toInt

  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      target.takeDamage(dmg(user))
      target.changeMp(-mpCost)
    user.endTurn()
    (this, user, target)

}