package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class BattleCry extends Skill(
  
  name = "Battle cry",
  attack = false,
  heals = true,
  negative = false,
  nerfs = false,
  mpCost = 50
  
) {
  
  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }
  
  def dmg(user: Character) = 0
  
  def effect(user: Character, target: Character): Skill =
    if user.hasMp then
      user.changeMp(-mpCost)
      target.changeHp(20)
      target.changeAtk(20)
      target.changeMgc(10)
      target.changeSpeed(10)
    user.endTurn()
    this
    
}
