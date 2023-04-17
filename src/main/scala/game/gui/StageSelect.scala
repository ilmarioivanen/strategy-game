package game.gui

import scalafx.Includes.*
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox
import scalafx.scene.layout.HBox
import stages.*

class StageSelect extends VBox {

  // Some way of getting all the possible stages
  // here or in gamegui or in game
  
  val bg = new Battleground

  val stage1 = new Button(bg.name)

  // This one will probably stay
  // Button for telling the game you are done selecting your stage
  val next = new Button("Next")

  // Labels for some info messages
  val messages = new Label("Please select a stage by clicking one of the buttons.")
  val part1 = new Label("Selected stage: ")
  val part2 = new Label("")
  
  val selected = new HBox {
    children = Array(part1, part2)
  }
  
  this.children = Array(stage1, next, messages, selected)
}
