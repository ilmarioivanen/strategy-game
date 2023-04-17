package characters

import skills.Skill
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

class Character(val name: String) {

  // Base values for characters
  private var dead = false
  private var done = false
  private var hp = 100
  private var mp = 100
  private var speed = 100
  // probably needs more stats such as attackDamage and base values that are separate for every stat
  
  // "Character sprite", just a shape for now
  private var currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = Blue
  }
 
  
  // Methods that are same for every character
  def isDead: Boolean =
    if hp <= 0 then
      dead = true
      dead
    else
      dead = false
      dead
  def usedTurn: Boolean = done
  def currentHp: Int = hp
  def currentMana: Int = mp
  def currentSpeed: Int = speed
  def takeDamage(dmg: Int): Int =
    this.hp -= dmg
    dmg // returns the damage so the ai can calculate based on that
  def sprite = currentSprite


  // Default skills

  // Skill names for buttons
  val skill1Name = "skill1"
  val skill2Name = "skill2"
  val skill3Name = "skill3"
  val skill4Name = "skill4"

  def skill1(target: Character): Int =
    this.done = true
    target.takeDamage(10)
  def skill2(target: Character): Int =
    this.done = true
    this.mp -= 20
    target.takeDamage(20)
  def skill3(target: Character): Int =
    this.done = true
    target.takeDamage(10)
  def skill4(target: Character): Int =
    this.done = true
    target.takeDamage(10)

}
