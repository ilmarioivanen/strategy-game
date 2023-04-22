package skills

import characters._
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

class Slash extends Skill(
  
  name = "Slash",
  attack = true,
  heals = false,
  negative = false,
  nerfs = false,
  instant = true,
  turns = 0
  
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
    target.takeDamage(dmg(user))
    this
    
}
