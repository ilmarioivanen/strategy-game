package game.gui

import scalafx.Includes.*
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{HBox, VBox}

class CharacterSelect extends VBox {
  
  // some way of getting al the characters
  // here or in gamegui or in game
  
  val character1 = new Button("character1") 
  
  val clear = new Button("Clear party")

  // Button for telling the game you have selected your characters and ready to start the game
  val startGame = new Button("Start Game") 
  
  // Labels for some info messages
  val messages = new Label("Add characters to your party by clicking the buttons.") 
  val part1 = new Label("Selected stage: ")
  val part2 = new Label("")
  
  val selected = new HBox {
    children = Array(part1, part2)
  }
  
  this.children = Array(character1, clear, startGame, messages, selected)
}
