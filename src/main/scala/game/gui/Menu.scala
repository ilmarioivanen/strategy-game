package game.gui

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox

class Menu extends VBox {

  val newGame = new Button("New Game") {
    onAction = (event) => println("dunno")
  }

  val saveGame = new Button("Save Game") {
    onAction = (event) => println("saved")
  }

  val loadGame = new Button("Load Game") {
    onAction = (event) => println("loaded")
  }

  this.children = Array(newGame, saveGame, loadGame)
}
