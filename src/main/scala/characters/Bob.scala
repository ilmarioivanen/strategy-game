package characters

import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color.*
import skills.*

class Bob extends Character(

  name = "Bob",
  baseHp = 80,
  baseMp = 80,
  baseAtkDmg = 80,
  baseMgcDmg = 80,
  baseSpeed = 80
  
) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Blue
  }
  
  override def skills = Array(new Heal, new Fireball, new QuickShot, new Dodge)
  
}
