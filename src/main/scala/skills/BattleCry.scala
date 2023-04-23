package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class BattleCry extends Skill(
  
  name = "Battle Cry",
  attack = false,
  heals = false, // Techically doesn't heal since this changes hp regardless of base hp
  negative = false,
  nerfs = false,
  mpCost = 50
  
) {
  
  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }
  
  def dmg(user: Character) = 0
  
  def effect(user: Character, target: Character): (Skill, Character, Character) =
    if user.hasMp(mpCost) then
      user.changeMp(-mpCost)
      target.changeHp(20)
      target.changeAtk(20)
      target.changeMgc(10)
      target.changeSpeed(10)
    user.endTurn()
    (this, user, target)
    
}
