package characters

import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import skills.*

class Warrior extends Character(

  name = "Warrior",
  baseHp = 150,
  baseMp = 50,
  baseAtkDmg = 100,
  baseMgcDmg = 50,
  baseSpeed = 50
  
) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Brown
  }
  
  override def skills = Array(new Slash, new Cripple, new BattleCry, new Heal)
  
}
