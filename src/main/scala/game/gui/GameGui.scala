package game.gui

import scalafx.Includes._
import game.Game
import scalafx.scene.Scene
import scalafx.scene.Group
import scalafx.scene.text.Font
import engine.InputManager
import scalafx.animation.AnimationTimer
import scala.concurrent.*
import ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

// The class was made for switching between normal gameview and the menu.
// In the future this might also include other stuff like the game loop
// Extends Runnable, so other actions can be done that
class GameGui(game: Game) extends Scene {

  private var gameOver = game.isOver

  val gameMenu = new Menu {
    newGame.onAction = (event) => startGame()
    //saveGame.onAction
    //loadGame.onAction
  }
  //val characterSelect = new CharacterSelect   for selecting the characters
  //val stageSelect = new StageSelect           for selecting the stage

  val gameView = new GameView


  this.root = gameMenu
  InputManager.handleInput(this)

  // Method to enter (and exit?) the menu
  def openMenu() =
    this.root = gameMenu

  def startGame() =
    this.root = gameView
    gameLoop()
}
