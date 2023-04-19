package players

import scala.collection.mutable.Buffer
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import characters._
import game.Game

class UserControlled(game: Game) extends Player {
  
  private val userParty = Buffer[Character]()

  // This method is useless as of now but could be used if I implemented things differently
  // This is never called
  def takeTurn() =
    Thread.sleep(5)
    
  def party = userParty
  def addToParty(character: Character) =
    if userParty.size < 3 then
      userParty += character
  def removeFromParty(character: Character) =
    if userParty.nonEmpty then
      userParty -= character
}
