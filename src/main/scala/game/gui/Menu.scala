package game.gui

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.scene.control.Label

class Menu extends VBox {

  // Menu buttons and their default event actions for testing etc
  
  val continue = new Button("Continue Game") {
    onAction = (event) => println("continue game")
  }
  
  val newGame = new Button("New Game") {
    onAction = (event) => println("new game")
  }
  
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
