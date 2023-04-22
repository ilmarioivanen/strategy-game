package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle

class Dodge extends Skill(

  name = "Dodge",
  attack = false,
  heals = false,
  negative = false,
  nerfs = false,
  mpCost = 0

) {

  override val visual = new Rectangle {
    width = 5
    height = 40
    fill = Black
    rotate = 100
  }

  def dmg(user: Character) = 0

  def effect(user: Character, target: Character): Skill =
    user.changeMp(20)
    user.endTurn()
    this

}
