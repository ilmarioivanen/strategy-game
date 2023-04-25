package characters

import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import skills.*

class Ranger extends Character(

  // Ranger is a balanced physical fighter
  name = "Ranger",
  baseHp = 120,
  baseMp = 50,
  baseAtkDmg = 120,
  baseMgcDmg = 50,
  baseSpeed = 100
  
) {

  // "Character sprite", just a shape for now
  override val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = DarkGreen
  }
  
  override def skills = Array(new QuickShot, new Cripple, new Barrage, new Poison)
  
}
