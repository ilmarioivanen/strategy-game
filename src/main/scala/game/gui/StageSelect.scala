package game.gui

import scalafx.Includes.*
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox
import scalafx.scene.layout.HBox
import stages.*
import scala.collection.mutable.Buffer


class StageSelect extends VBox {
 
  private val stages = Array(new Battleground, new Volcano, new Desert)

  private val stageButtons = Buffer[Button]()
  
  for stage <- stages do 
    val stageButton = new Button(stage.name)
    stageButtons += stageButton
    
  val stagesToButtons = stages.zip(stageButtons)

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
  
  this.children = stageButtons ++ Array(next, messages, selected)
}
