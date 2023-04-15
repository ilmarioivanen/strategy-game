package game.gui

import scala.collection.mutable.Buffer
import scalafx.Includes._
import game.Game
import scalafx.scene.{Group, Node, Parent, Scene}
import scalafx.scene.text.Font
import scalafx.scene.control.Label
import engine.InputManager
import javafx.scene.shape.Circle
import scalafx.animation.AnimationTimer
import scalafx.geometry.Bounds
import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color._
import scalafx.scene.effect


// The class was made for switching between normal gameview and the menu.
// In the future this might also include other stuff like the game loop
class GameGui(game: Game) extends Scene {

  // Set up info from game

  val user = game.user
  val enemy = game.ai
  val userParty = game.user.party
  val enemyParty = game.ai.party
  val bothParties = userParty ++ enemyParty

  val gameMenu = new Menu {

    continue.onAction = (event) =>
      if game.isStarted then
        openView(gameView)
      else
        this.messages.text = "There's no game to continue. Please start a new game."

    newGame.onAction = (event) =>
      openView(stageSelect)
      this.messages.text = ""
    //saveGame.onAction
    //loadGame.onAction
  }
  val stageSelect = new StageSelect {
    next.onAction = (event) => openView(characterSelect)
  }
  val characterSelect = new CharacterSelect {
    next.onAction = (event) =>
      openView(gameView)
      game.startGame()
  }

  // Set up stages??


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
  val characterNodes = Buffer[Node]()
  // for some reason couldn't get Map to work properly

  def setCharacters() =
    for character <- userParty do
      val sprite = character.sprite
      // set position of characters
      userParty.indexOf(character) match
        case 0 =>
          sprite.translateX = 50
          sprite.translateY = -100
        case 1 =>
          sprite.translateX = 150
          sprite.translateY = 0
        case 2 =>
          sprite.translateX = 50
          sprite.translateY = 100
      gameView.children += sprite
      characterNodes += sprite
    for character <- enemyParty do
      val sprite = character.sprite
      enemyParty.indexOf(character) match
        case 0 =>
          sprite.translateX = 500
          sprite.translateY = -100
        case 1 =>
          sprite.translateX = 400
          sprite.translateY = 0
        case 2 =>
          sprite.translateX = 500
          sprite.translateY = 100
        case _ => // some error
      gameView.children += sprite
      characterNodes += sprite

  // Initial characters
  setCharacters()
  val characterMap = characterNodes.zip(bothParties)

  def targeted(targetNode: Node) =
    // reset old target effects
    gameView.children.foreach(_.setEffect(null))
    val targetEffect = new DropShadow {
                color = Red
                radius = 30
                spread = 0.75
    }
    targetNode.effect = targetEffect


  // maybe a bit dumb to clear the info every time but it is what it is
  def updateInfo() =
    gameView.userPartyInfo.children.clear()
    gameView.enemyPartyInfo.children.clear()
    setTextInfo()

  // Update method that updates everything?
  // def update() = updateInfo()... other updates

  // Set up button events
  attackButton.onAction = (event) =>
    user.party.head.basicAttack(enemyParty.head) // for testing, Action class need to be implemented
    updateInfo()

  this.root = gameMenu
  InputManager.handleInput(this)

  // Method(s) to enter (and exit?) the menu and selecting views

  def openView(view: Parent) =
    this.root = view

}
