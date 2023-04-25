package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle
import scala.util.Random

class CaffeineOverdose extends Skill(

  name = "CaffeineOd.",
  attack = false,
  heals = false,
  negative = true,
  nerfs = false,
  mpCost = 0

) {

  // Placeholder visual
  override val visual = new Rectangle {
    fill = Black
  }

  def dmg(user: Character) = 0

  def effect(user: Character, target: Character): (Skill, Character, Character) =
    val random = Random(System.nanoTime())
    val modifier = random.nextInt(200)
    target.takeDamage(10)
    target.changeAtk(modifier)
    target.changeMgc(modifier)
    target.changeSpeed(modifier)
    user.endTurn()
    (this, user, target)

}
