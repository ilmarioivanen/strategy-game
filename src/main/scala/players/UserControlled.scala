package players

import scala.collection.mutable.Buffer
import characters.*

class UserControlled extends Player {
  
  private val userParty = Buffer[Character]()

  def takeTurn() =
    Thread.sleep(5000) // simulate player taking the turn
    "player turn"
    
  def party = userParty
  def addToParty(character: Character) = userParty += character
  
}
