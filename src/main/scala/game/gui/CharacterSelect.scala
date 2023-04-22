package game.gui

import characters.*
import scalafx.Includes.*
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{HBox, VBox}
import scala.collection.mutable.Buffer

class CharacterSelect extends VBox {


  def setOfCharacters = Array(new Bob, new Gigachu, new Ranger, new Rogue, new Warrior, new Wizard)

  private val characterButtons = Buffer[Button]()

  private val characters =
    val set1 = setOfCharacters
    val set2 = setOfCharacters
    val set3 = setOfCharacters
    set1.zip(set2).zip(set3).map(c => (c._1._1, c._1._2, c._2))


  for character <- characters do
    val characterButton = new Button(character._1.name)
    characterButtons += characterButton

  def charToButtons = characters.zip(characterButtons)
  
  val clear = new Button("Clear party")

  // Button for telling the game you have selected your characters and ready to start the game
  val startGame = new Button("Start Game") 
  
  // Labels for some info messages
  val messages = new Label("Add characters to your party by clicking the buttons.") 
  val part1 = new Label("Your party: ")
  val part2 = new Label("")
  
  val selected = new HBox {
    children = Array(part1, part2)
  }
  
  this.children = characterButtons ++ Array(clear, startGame, messages, selected)
}
