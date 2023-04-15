package game.gui

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox

class StageSelect extends VBox {

  // Placeholder button
  // The buttons will be added in the gui class that gets the information of the playable stages
  val stage1 = new Button("stage1") {
    onAction = (event) => println("stage1")
  }
  // This one will probably stay
  // Button for telling the game you are done selecting your stage
  val next = new Button("Next") {
    onAction = (event) => println("next")
  }

  this.children = Array(stage1, next)
}
