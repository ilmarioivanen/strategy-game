package players

import scala.collection.mutable.Buffer
import scala.util.Random.shuffle
import characters._
import game.Game

class EnemyAI(game: Game) extends Player {
  
  private val aiParty = Buffer[Character]()

  def takeTurn() =
    val userParty = game.userParty
    val aiChar = game.characterTurn
    val target = shuffle(userParty).head
    aiChar.skill1(target)
    
  def party = aiParty
  def addToParty(character: Character) =
    aiParty += character
  def removeFromParty(character: Character) =
    aiParty -= character
}
