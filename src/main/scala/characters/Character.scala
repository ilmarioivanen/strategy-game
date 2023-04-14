package characters

import scalafx.scene.Node


abstract class Character(val name: String) {

/* Maybe useless

  private var dead = false
  private var hp = 100
  private var mp = 100
  private var speed = 100

*/
  def currentHp: Int
  def currentMana: Int
  def basicAttack(target: Character): Unit
  def specialAttack(target: Character): Unit
  def takeDamage(dmg: Int): Unit
  def sprite: Node                         // This will be changed to something else if time
}
