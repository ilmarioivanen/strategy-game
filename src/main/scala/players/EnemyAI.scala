package players

import scala.collection.mutable.Buffer
import scala.util.Random.shuffle
import characters._
import game.Game

class EnemyAI(game: Game) extends Player {
  
  private val aiParty = Buffer[Character]()

  def takeTurn() =
    Thread.sleep(100) // simulates calculation time for now
    val userParty = game.userParty
    val aiChar = game.characterTurn
    val target = shuffle(userParty).head
    aiChar.skill1(target)
    
  def party = aiParty
  def addToParty(character: Character) =
    if aiParty.size < 3 then
      aiParty += character
  def removeFromParty(character: Character) =
    if aiParty.nonEmpty then
      aiParty -= character
}
