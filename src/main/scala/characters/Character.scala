package characters

abstract class Character {
  
  private var dead = false
  private var hp = 100
  private var mp = 100
  private var speed = 100
  
  def basicAttack(): Int
  def specialAttack(): Int
  def takeDamage(dmg: Int): Unit
}
