package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Barrage extends Skill(
  
  name = "Barrage",
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
    (user.baseAtkDmg * 0.1).toInt
  
  def effect(user: Character, target: Character): (Skill, Character, Character) =
    while user.hasMp(mpCost) do
      user.changeMp(-mpCost)
      target.takeDamage(dmg(user))
    user.endTurn()
    (this, user, target) 
    
}
