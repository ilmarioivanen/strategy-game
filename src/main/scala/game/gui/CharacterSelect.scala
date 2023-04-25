package game.gui

import game.Content
import characters.*
import scalafx.Includes.*
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii, HBox, VBox}
import scala.collection.mutable.Buffer
import scalafx.geometry.Pos.*
import scalafx.geometry.Insets
import scalafx.scene.paint.Color.LightBlue


class CharacterSelect extends VBox {

  val content = new Content

  val menuBackground = Background(Array(new BackgroundFill((LightBlue), CornerRadii.Empty, Insets(10))))
  background = menuBackground
  alignment = TopLeft
  padding = Insets(10)

  private val characterButtons = Buffer[Button]()

  // Call the allCharacters method three times and make three sets
  // This way the player can choose three same characters and they are not the same objects
  private val characters =
    val set1 = content.allCharacters
    val set2 = content.allCharacters
    val set3 = content.allCharacters
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
  characterButtons.foreach(_.setMinSize(60, 30))

  children = characterButtons ++ Array(clear, startGame, messages, selected)
}
