package characters

import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import skills._

class Character(

  // The class takes all the base values as constructors
  val name: String,
  val baseHp: Int,
  val baseMp: Int,
  val baseAtkDmg: Int,
  val baseMgcDmg: Int,
  val baseSpeed: Int

) {

  // "Character sprite", just a shape for now
  val currentSprite = new Rectangle {
    width = 50
    height = 50
    fill = White
  }

  // Variables for values
  private var dead = false
  private var done = false
  private var hp = baseHp
  private var mp = baseMp
  private var atkDmg = baseAtkDmg
  private var mgcDmg = baseMgcDmg
  private var speed = baseSpeed

  // Methods that are same for every character
  def isDead: Boolean =
    if hp <= 0 then
      dead = true
      dead
    else
      dead = false
      dead
  def endTurn() =
    done = true
  def usedTurn: Boolean = done
  def currentHp: Int = hp
  def currentMana: Int = mp
  def currentAtk: Int = atkDmg
  def currentMgc: Int = mgcDmg
  def currentSpeed: Int = speed
  def takeDamage(dmg: Int): Unit =
    this.hp -= dmg
  def sprite = currentSprite


  // Skills and their names
  // These are the base skills that have to be overridden
  val skills = Array(new Slash, new Slash, new Slash, new Slash)

  val s1 = skills(0)
  val s2 = skills(1)
  val s3 = skills(2)
  val s4 = skills(3)

  val skill1Name = s1.name
  val skill2Name = s2.name
  val skill3Name = s3.name
  val skill4Name = s4.name

  def skill1(target: Character): Skill =
    s1.effect(this, target)
  def skill2(target: Character): Skill =
    s2.effect(this, target)
  def skill3(target: Character): Skill =
    s3.effect(this, target)
  def skill4(target: Character): Skill =
    s4.effect(this, target)

}
