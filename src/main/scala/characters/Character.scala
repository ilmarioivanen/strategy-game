package characters

import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import skills._

// Class that defines game characters' variables and methods
class Character(

  // The class takes all the base values as constructor parameters
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

  // Variables for character state and values (stats)
  private var dead = false
  private var done = false // Set to true when the character has used it's turn
  private var hp = baseHp
  private var mp = baseMp
  private var atkDmg = baseAtkDmg
  private var mgcDmg = baseMgcDmg
  private var speed = baseSpeed

  // Methods that are same for every character
  // Used for changing stats and getting current stats
  def isDead: Boolean =
    if hp <= 0 then
      dead = true
      dead
    else
      dead = false
      dead
  def resetTurn() =
    done = false
  def endTurn() =
    done = true
  def hasMp(cost: Int) =
    currentMp >= cost
  def usedTurn: Boolean = done
  def currentHp: Int = hp
  def currentMp: Int = mp
  def currentAtk: Int = atkDmg
  def currentMgc: Int = mgcDmg
  def currentSpeed: Int = speed
  def sprite = currentSprite
  
  // Methods for changing the stats
  def changeHp(d: Int) = // Basically just same as takeDamage method but kept it for clarity
    hp += d
  def heal(d: Int) = 
    hp = baseHp.min(currentMp + d) // Healing can't go over base health
  def changeMp(d: Int) =
    mp += d
  def changeAtk(d: Int) =
    atkDmg += d
  def changeMgc(d: Int) =
    mgcDmg += d
  def changeSpeed(d: Int) =
    speed += d
  def takeDamage(dmg: Int) =
    hp -= dmg

  // Methods for setting stats (made for FileManager class)
  def setHp(s: Int) =
    hp = s
  def setMp(s: Int) =
    mp = s
  def setAtk(s: Int) =
    atkDmg = s
  def setMgc(s: Int) =
    mgcDmg = s
  def setSpeed(s: Int) =
    speed = s
  def setDone(b: Boolean) =
    done = b

  // Skills and their names
  // These are the base skills that have to be overridden for every character
  def skills: Array[Skill] = Array(new Slash, new Slash, new Slash, new Slash)
  
  val s1 = skills(0)
  val s2 = skills(1)
  val s3 = skills(2)
  val s4 = skills(3)

  // Method for telling the player if skill is attack or buff
  def skillType(skill: Skill): String =
    if skill.attack then
      "#A"
    else
      "#B"

  val skill1Name = s"${s1.name}\n${s1.mpCost} Mp  ${skillType(s1)}"
  val skill2Name = s"${s2.name}\n${s2.mpCost} Mp  ${skillType(s2)}"
  val skill3Name = s"${s3.name}\n${s3.mpCost} Mp  ${skillType(s3)}"
  val skill4Name = s"${s4.name}\n${s4.mpCost} Mp  ${skillType(s4)}"

  def skill1(target: Character): (Skill, Character, Character) =
    s1.effect(this, target)
  def skill2(target: Character): (Skill, Character, Character) =
    s2.effect(this, target)
  def skill3(target: Character): (Skill, Character, Character) =
    s3.effect(this, target)
  def skill4(target: Character): (Skill, Character, Character) =
    s4.effect(this, target)

}
