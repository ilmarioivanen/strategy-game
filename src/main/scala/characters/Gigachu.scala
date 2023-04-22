package characters

import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import skills.*

class Gigachu extends Character(
  
  name = "Gigachu",
  baseHp = 75,
  baseMp = 150,
  baseAtkDmg = 50,
  baseMgcDmg = 120,
  baseSpeed = 200

) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Yellow
  }

  override def skills = Array(new Slash, new Thunder, new CaffeineOverdose, new BattleCry)
  
}
