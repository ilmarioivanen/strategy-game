package game.gui

import scalafx.Includes.*
import scalafx.scene.control.Button
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii, VBox}
import scalafx.scene.control.Label
import scalafx.geometry.Pos.*
import scalafx.geometry.Insets
import scalafx.scene.paint.Color.*

class Menu extends VBox {

  val menuBackground = Background(Array(new BackgroundFill((LightBlue), CornerRadii.Empty, Insets(10))))
  background = menuBackground
  alignment = Center
  spacing = 20
  
  val continue = new Button("Continue Game")

  val newGame = new Button("New Game")

  val saveGame = new Button("Save Game")

  val loadGame = new Button("Load Game")

  // Label for some info messages
  val messages = new Label("")

  val nodes = Array(continue, newGame, saveGame, loadGame, messages)

  nodes.foreach(_.setMinSize(100, 40))

  children = nodes
}
