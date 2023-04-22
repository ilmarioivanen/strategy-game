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
  
  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }
  
  def dmg(user: Character) = 
    (user.baseMgcDmg * 0.3).toInt
  
  def effect(user: Character, target: Character): Skill =
    if user.hasMp then
      user.changeMp(-mpCost)
      target.takeDamage(dmg(user))
    user.endTurn()
    this

}
