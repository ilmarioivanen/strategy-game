package characters

import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

class Gigachu extends Character(
  
  name = "Gigachu",
  baseHp = 75,
  baseMp = 150,
  baseAtkDmg = 50,
  baseMgcDmg = 150,
  baseSpeed = 200

) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Yellow
  }

}
