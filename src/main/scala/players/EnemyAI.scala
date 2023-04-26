package players

import scala.collection.mutable.Buffer
import scala.util.Random.shuffle
import characters._
import game.Game

// Class for the enemy AI
class EnemyAI(game: Game) extends Player {
  
  private val aiParty = Buffer[Character]()

  def takeTurn() =
    val userParty = game.userParty
    val aiChar = game.characterTurn  // This is only called when ai's character is in turn
    // All of the skills that can be casted, but if there is none then just all the skills regardless
    val skills =
      val canCast = aiChar.skills.filter(s => s.mpCost <= aiChar.currentMp)
      if canCast.nonEmpty then
        canCast
      else
        aiChar.skills
    // Attacking skills
    val attacks = skills.filter(s => s.attack)
    val nerfs = skills.filter(s => s.nerfs)
    val heals = skills.filter(s => s.heals)
    // Other skills
    val other = skills.filter(s => !s.attack && !s.heals)
    // Attack with most dmg and no negative effects on allies
    val mostDmg = attacks.filterNot(s => s.negative).maxBy(s => s.dmg(aiChar))
    // Random attack is default
    var skill = shuffle(attacks).head

    // Finishing low targets is 1st priority
    if userParty.exists(c => c.currentHp <= mostDmg.dmg(aiChar)) then
      skill = mostDmg
    // Chooses heal if someone in party is low and character has a healing skill
    else if aiParty.exists( c => c.currentHp <= c.baseHp / 2) && heals.nonEmpty then
      skill = shuffle(heals).head
    // If character is healthy, ai tries to buff stats or nerf target
    // if neither are available just attack
    else if aiChar.currentHp >= 0.75 * aiChar.baseHp then
      if other.nonEmpty then
        skill = shuffle(other).head
      else if nerfs.nonEmpty then
        skill = shuffle(nerfs).head
      else
        skill = mostDmg
    else
      // The character will choose an high dmg attack regardless of negative effects
      skill = attacks.maxBy(s => s.dmg(aiChar))

    // Target lowest hp enemy if attack skill
    // if heals target lowest ally
    // if neither the skill is likely a buff so target the healthiest ally
    val target =
      if skill.attack then
        userParty.minBy(_.currentHp)
      else if skill.heals then
        aiParty.minBy(_.currentHp)
      else if skill.nerfs then
        userParty.maxBy(_.currentHp)
      else
        aiParty.maxBy(_.currentHp)

    skill.name match
      case aiChar.s1.name => game.skillsInBattle += aiChar.skill1(target)
      case aiChar.s2.name => game.skillsInBattle += aiChar.skill2(target)
      case aiChar.s3.name => game.skillsInBattle += aiChar.skill3(target)
      case aiChar.s4.name => game.skillsInBattle += aiChar.skill4(target)
      case _ => new Exception("AI tried to choose a skill that doesn't exist")

  // Other methods
  def party = aiParty
  def addToParty(character: Character) =
    if aiParty.size < 3 then
      aiParty += character
  def removeFromParty(character: Character) =
    if aiParty.nonEmpty then
      aiParty -= character
}
