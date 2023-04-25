package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class CalmMind extends Skill(
  
  name = "CalmMind",
  attack = false,
  heals = false,
  negative = false,
  nerfs = false,
  mpCost = 30
  
) {
  
  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }
  
  def dmg(user: Character) = 0
  
  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      target.changeMgc(20)
      target.changeAtk(20)
    user.endTurn()
    (this, user, target)

}
