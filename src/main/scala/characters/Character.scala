package characters

import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import skills._

abstract class Character(

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
    fill = Blue
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
  def takeDamage(dmg: Int): Int =
    this.hp -= dmg
    dmg // returns the damage so the ai can calculate based on that
  def sprite = currentSprite


  // Skills and their names.
  // These have to be overridden
  val slash = new Slash

  val skill1Name = slash.name
  val skill2Name = slash.name
  val skill3Name = slash.name
  val skill4Name = slash.name

  def skill1(target: Character) =
    slash.effect(this, target)
  def skill2(target: Character) =
    slash.effect(this, target)
  def skill3(target: Character) =
    slash.effect(this, target)
  def skill4(target: Character) =
    slash.effect(this, target)

}
