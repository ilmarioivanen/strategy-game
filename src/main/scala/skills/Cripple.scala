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
  
  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }
  
  def dmg(user: Character) = 
    (user.baseAtkDmg * 0.1).toInt
  
  def effect(user: Character, target: Character): Skill =
    target.takeDamage(dmg(user))
    target.changeSpeed(-50)
    user.endTurn()
    this
    
}
