package game.gui

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox

class CharacterSelect extends VBox {
  
  // Placeholder button
  // The buttons will be added in the gui class that gets the information of the playable stages
  val character1 = new Button("character1") {
    onAction = (event) => println("character1")
  }
  // This one will probably stay
  // Button for telling the game you are done selecting your characters
  val next = new Button("Next") {
    onAction = (event) => println("next")
  }
  
  this.children = Array(character1, next)
}
