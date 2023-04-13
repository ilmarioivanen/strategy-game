package players

import characters.*
import scala.collection.mutable.Buffer

trait Player {

  def takeTurn(): String
  def party: Buffer[Character]
  def addToParty(character: Character): Unit

}
