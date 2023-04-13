package characters

object Bob extends Character {

  private var dead = false
  private var hp = 100
  private var mp = 100
  private var speed = 100
  
  def basicAttack(): Int =
    10
  def specialAttack(): Int =
    this.mp -= 20
    20
  def takeDamage(dmg: Int) =
    this.hp -= dmg
}
