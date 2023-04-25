package game.gui

import scalafx.Includes.*
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii, HBox, VBox}
import stages.*
import game.Content
import scala.collection.mutable.Buffer
import scalafx.geometry.Pos.*
import scalafx.geometry.Insets
import scalafx.scene.paint.Color.LightBlue


class StageSelect extends VBox {

  val menuBackground = Background(Array(new BackgroundFill((LightBlue), CornerRadii.Empty, Insets(10))))
  background = menuBackground
  alignment = TopLeft
  padding = Insets(10)

  // Get all possible content
  // Only stages are relevant for this class
  val content = new Content
  val stages = content.allStages

  // Buffer for buttons for choosing the stage
  private val stageButtons = Buffer[Button]()
  
  for stage <- stages do 
    val stageButton = new Button(stage.name)
    stageButtons += stageButton
    
  val stagesToButtons = stages.zip(stageButtons)

  // Button for telling the game you are done selecting your stage
  val next = new Button("Next")
  // Labels for some info messages
  val messages = new Label("Please select a stage by clicking one of the buttons.")
  val part1 = new Label("Selected stage: ")
  val part2 = new Label("")
  
  val selected = new HBox {
    children = Array(part1, part2)
  }

  stageButtons.foreach(_.setMinSize(100, 30))

  children = stageButtons ++ Array(next, messages, selected)
}
