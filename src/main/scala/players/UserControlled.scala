package players

import scala.collection.mutable.Buffer
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import characters._
import game.Game

class UserControlled(game: Game) extends Player {
  
  private val userParty = Buffer[Character]()

  def takeTurn() =
    Thread.sleep(5) // simulate animation time
    

    
  def party = userParty
  def addToParty(character: Character) =
    userParty += character
  def removeFromParty(character: Character) =
    userParty -= character
}
