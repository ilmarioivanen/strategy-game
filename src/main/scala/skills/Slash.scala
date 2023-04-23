package skills

import characters.*
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

class Slash extends Skill(
  
  name = "Slash",
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
    (user.baseAtkDmg * 0.2).toInt
  
  def effect(user: Character, target: Character): (Skill, Character, Character) =
    target.takeDamage(dmg(user))
    user.endTurn()
    (this, user, target)
    
}
