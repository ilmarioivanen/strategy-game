package skills

import characters.Character
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

class Explosion extends Skill(
  
  name = "Exlposion",
  attack = true,
  heals = false,
  negative = true,
  nerfs = false,
  mpCost = 100
  
) {
  
  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }
  
  def dmg(user: Character) = 
    (user.baseMgcDmg * 2)
  
  def effect(user: Character, target: Character): Skill =
    if user.hasMp then
      user.changeMp(-mpCost)
      user.takeDamage(dmg(user))
      target.takeDamage(dmg(user))
    user.endTurn()
    this
    
}
