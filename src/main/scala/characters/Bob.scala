package characters

import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._

class Bob extends Character("Bob") {

  private var dead = false
  private var hp = 100
  private var mp = 100
  private var speed = 100
  private var currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Blue
  }
  // probably needs more stats such as attackDamage and base values that are separate for every stat

  def currentHp = hp
  def currentMana = mp
  
  def basicAttack(target: Character): Unit =
    target.takeDamage(10)
  def specialAttack(target: Character): Unit =
    this.mp -= 20
    target.takeDamage(20)
  def takeDamage(dmg: Int) =
    this.hp -= dmg

  def sprite = currentSprite
}
