package players

import characters._
import scala.collection.mutable.Buffer

// Trait that defines players' (human and ai) methods
trait Player {

  def takeTurn(): Unit
  def party: Buffer[Character]
  def addToParty(character: Character): Unit
  def removeFromParty(character: Character): Unit

}