package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Cripple extends Skill(
  
  name = "Cripple",
  attack = true,
  heals = false,
  negative = false,
  nerfs = true,
  mpCost = 0
  
) {
  
  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }
  
  def dmg(user: Character) = 
    (user.baseAtkDmg * 0.1).toInt
  
  def effect(user: Character, target: Character): (Skill, Character, Character) =
    target.takeDamage(dmg(user))
    target.changeSpeed(-50)
    user.endTurn()
    (this, user, target)
    
}
