package characters

import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

class Wizard extends Character(

  name = "Wizard",
  baseHp = 100,
  baseMp = 100,
  baseAtkDmg = 100,
  baseMgcDmg = 100,
  baseSpeed = 100
  
) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Gray
  }
}
