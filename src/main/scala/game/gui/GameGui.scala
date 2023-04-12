package game.gui

import scalafx.Includes._
import game.Game
import scalafx.scene.Scene
import engine.InputManager

// The class was made for switching between normal gameview and the menu.
// In the future this might also include other stuff like the game loop
class GameGui(game: Game) extends Scene {

  val gameMenu = new Menu {
    newGame.onAction = (event) => startGame()
    //saveGame.onAction
    //loadGame.onAction
  }

  //val characterSelect = new CharacterSelect   for selecting the characters
  //val stageSelect = new StageSelect           for selecting the stage
  val gameView = new GameView // Group instead ???

  this.root = gameMenu
  InputManager.handleInput(this)

  // Method to enter (and exit?) the menu
  def toMenu() =
    if this.root != gameMenu then
      this.root = gameMenu
    else
      this.root = gameView

  def startGame() =
    this.root = gameView
}
