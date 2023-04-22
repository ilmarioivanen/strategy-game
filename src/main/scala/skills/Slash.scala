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
  
  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }
  
  def dmg(user: Character) = 
    (user.baseAtkDmg * 0.2).toInt
  
  def effect(user: Character, target: Character): Skill =
    target.takeDamage(dmg(user))
    user.endTurn()
    this
    
}
