package game.gui

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.scene.control.Label

class Menu extends VBox {
  
  val continue = new Button("Continue Game")
  
  val newGame = new Button("New Game")
 
  val saveGame = new Button("Save Game") {
    onAction = (event) => println("saved")
  }

  val loadGame = new Button("Load Game") {
    onAction = (event) => println("loaded")
  }

  // Label for some info messages
  val messages = new Label("") 
  
  this.children = Array(continue, newGame, saveGame, loadGame, messages)
}
