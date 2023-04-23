package skills

import characters.Character
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

class Fireball extends Skill(
  
  name = "Fireball",
  attack = true,
  heals = false,
  negative = false,
  nerfs = false,
  mpCost = 10
  
) {
  
  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }
  
  def dmg(user: Character) = 
    (user.baseMgcDmg * 0.3).toInt
  
  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      target.takeDamage(dmg(user))
    user.endTurn()
    (this, user, target)

}
