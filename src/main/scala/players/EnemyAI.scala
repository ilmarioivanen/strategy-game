package players

import scala.collection.mutable.Buffer
import characters.*

class EnemyAI extends Player {
  
  private val aiParty = Buffer[Character]()

  def takeTurn() =
    Thread.sleep(2000) // simulate the calculations
    "enemy turn"
    
  def party = aiParty
  def addToParty(character: Character) = aiParty += character
}
