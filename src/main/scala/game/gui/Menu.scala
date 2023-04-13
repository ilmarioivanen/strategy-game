package game.gui

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox

class Menu extends VBox {

  val newGame = new Button("New Game")

  val saveGame = new Button("Save Game") {
    onAction = (event) => println("saved")  //placeholder
  }

  val loadGame = new Button("Load Game") {
    onAction = (event) => println("loaded") //placeholder
  }

  this.children = Array(newGame, saveGame, loadGame)
}
