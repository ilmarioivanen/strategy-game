package characters

import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import skills.*

class Rogue extends Character(

  // Rogue is a physical glass cannon
  name = "Rogue",
  baseHp = 80,
  baseMp = 50,
  baseAtkDmg = 150,
  baseMgcDmg = 50,
  baseSpeed = 120
  
) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Black
  }
  
  override def skills = Array(new Slash, new ShadowStrike, new Poison, new Dodge)
  
}
