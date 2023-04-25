package skills

import characters.Character
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

class Explosion extends Skill(
  
  name = "Explosion",
  attack = true,
  heals = false,
  negative = true,
  nerfs = false,
  mpCost = 100
  
) {
  
  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }
  
  def dmg(user: Character) = 
    (user.baseMgcDmg * 2)
  
  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      user.takeDamage(dmg(user))
      target.takeDamage(dmg(user))
    user.endTurn()
    (this, user, target)
    
}
