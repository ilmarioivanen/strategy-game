package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class CalmMind extends Skill(
  
  name = "Calm mind",
  attack = false,
  heals = false,
  negative = false,
  nerfs = false,
  mpCost = 30
  
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
      target.changeMgc(20)
    user.endTurn()
    this

}
