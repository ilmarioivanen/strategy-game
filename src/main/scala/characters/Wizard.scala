package characters

import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import skills.*

class Wizard extends Character(

  // Wizard is a slow but powerful magic user
  name = "Wizard",
  baseHp = 80,
  baseMp = 150,
  baseAtkDmg = 50,
  baseMgcDmg = 150,
  baseSpeed = 75
  
) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Gray
  }

  override def skills = Array(new Fireball, new CalmMind, new Explosion, new Heal)

}
