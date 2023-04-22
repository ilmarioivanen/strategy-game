package skills

import characters.Character
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Rectangle
import scala.util.Random

class CaffeineOverdose extends Skill(

  name = "Caffeine Od.",
  attack = false,
  heals = false,
  negative = true,
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
    val random = Random(System.nanoTime())
    val modifier = random.nextInt(200)
    target.takeDamage(10)
    target.changeAtk(modifier)
    target.changeMgc(modifier)
    target.changeSpeed(modifier)
    user.endTurn()
    this

}
