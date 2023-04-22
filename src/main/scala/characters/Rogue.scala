package characters

import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import skills.*

class Rogue extends Character(

  name = "Rogue",
  baseHp = 75,
  baseMp = 50,
  baseAtkDmg = 120,
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
