package game.gui

import scalafx.Includes.*
import game.Game
import scalafx.scene.Scene
import scalafx.scene.Group
import scalafx.scene.text.Font
import scalafx.scene.control.Label
import engine.InputManager
import scalafx.animation.AnimationTimer
import scala.concurrent.*
import ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

// The class was made for switching between normal gameview and the menu.
// In the future this might also include other stuff like the game loop
// Extends Runnable, so other actions can be done that
class GameGui(game: Game) extends Scene {

  // Set up info from game
  val user = game.user
  val enemy = game.ai
  val userParty = game.user.party
  val enemyParty = game.ai.party
  val bothParties = userParty ++ enemyParty

  val gameMenu = new Menu {
    newGame.onAction = (event) => startGame()
    //saveGame.onAction
    //loadGame.onAction
  }
  //val characterSelect = new CharacterSelect   for selecting the characters
  //val stageSelect = new StageSelect           for selecting the stage

  // Set up the base gameview
  val gameView = new GameView

  // Set up buttons
  val attackButton = gameView.button1
  val specialButton = gameView.button2
  val blButton = gameView.button3
  val brButton = gameView.button4

  // Set up text info
  def setTextInfo() =
    val userInfo = gameView.userPartyInfo
    val enemyInfo = gameView.enemyPartyInfo
    userInfo.children += Label("Your party:")
    enemyInfo.children += Label("Enemy party:")
    for character <- userParty do
      userInfo.children += Label(s"${character.name}:   ${character.currentHp} HP    ${character.currentMana} MP")
    for character <- enemyParty do
      enemyInfo.children += Label(s"${character.name}:   ${character.currentHp} HP    ${character.currentMana} MP")

  // Initial info
  setTextInfo()

  // Set up sprites
  def setCharacters() =
    for character <- bothParties do
      val sprite = character.sprite
      // set position of characters
      bothParties.indexOf(character) match
        case 0 =>
          sprite.translateX = 50
          sprite.translateY = -100
        case 1 =>
          sprite.translateX = 150
          sprite.translateY = 0
        case 2 =>
          sprite.translateX = 50
          sprite.translateY = 100
        case 3 =>
          sprite.translateX = 500
          sprite.translateY = -100
        case 4 =>
          sprite.translateX = 400
          sprite.translateY = 0
        case 5 =>
          sprite.translateX = 500
          sprite.translateY = 100
        case _ => // some error
      gameView.children += sprite

  // Initial characters
  setCharacters()

  // it is quite dumb to clear the info every time but it is what it is
  def updateInfo() =
    gameView.userPartyInfo.children.clear()
    gameView.enemyPartyInfo.children.clear()
    setTextInfo()


  // Set up button events
  attackButton.onAction = (event) =>
    user.party.head.basicAttack(enemy.party.head) // for testing, Action class need to be implemented
    updateInfo()

  this.root = gameMenu
  InputManager.handleInput(this)

  // Method to enter (and exit?) the menu
  def openMenu() =
    this.root = gameMenu

  def startGame() =
    this.root = gameView

}
