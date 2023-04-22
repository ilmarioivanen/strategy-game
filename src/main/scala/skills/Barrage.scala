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
  
  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }
  
  def dmg(user: Character) = 
    (user.baseAtkDmg * 0.1).toInt
  
  def effect(user: Character, target: Character): Skill =
    while user.hasMp do
      user.changeMp(-mpCost)
      target.takeDamage(dmg(user))
    user.endTurn()
    this
    
}
