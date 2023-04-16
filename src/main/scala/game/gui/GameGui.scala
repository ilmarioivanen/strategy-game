package game.gui

import scala.collection.mutable.Buffer
import scalafx.Includes._
import game.Game
import scalafx.scene.{Group, Node, Parent, Scene}
import scalafx.scene.text.Font
import scalafx.scene.control.Label
import engine.InputManager
import javafx.scene.shape.Circle
import scalafx.geometry.Bounds
import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color._
import scalafx.scene.effect
import scalafx.scene.control.Alert._
import scalafx.scene.control.Alert

// The class was made for switching between normal gameview and the menu.
// In the future this might also include other stuff like the game loop
class GameGui(game: Game) extends Scene {

  // Set up info from game
  val user = game.currentUser
  val enemy = game.currentEnemy
  val userParty = game.userParty
  val enemyParty = game.aiParty
  val bothParties = game.bothParties
    // Set up sprites
  val characterNodes = Buffer[Node]()


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

  // Set up stages?

  // Set up the base gameview
  val gameView = new GameView

  // Set up buttons
  val skill1Button = gameView.button1
  val skill2Button = gameView.button2
  val skill3Button = gameView.button3
  val skill4Button = gameView.button4

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

  def inTurn() =
    // reset old turn effects
    gameView.children.foreach(_.setEffect(null))
    val turnEffect = new DropShadow {
                color = Black
                radius = 20
                spread = 0.5
    }
    val nodeInTurn = characterMap
      .map((n, c) => (c, n))
      .toMap.get(game.characterTurn)
    nodeInTurn match
      case Some(node) =>
        node.effect = turnEffect
        node       // returns also the node which has the effect
      case None => // some error since some character should always be in turn

  def targeted(targetNode: Node) =
    // reset old target effects, but keep the turn effect
    gameView.children.foreach(n => if n != inTurn() then n.setEffect(null))
    // This is pretty inefficent as it calls inTurn() every time we want to change targets
    targetNode.effect = new DropShadow {
                color = Red
                radius = 30
                spread = 0.75
    }

  def target =
    InputManager.lastTarget match
      case Some(character) =>
        // If-else so there's a new target in case the last target died
        if character.isDead then
          enemyParty.head
        else
          character
      case None => enemyParty.head
      // the first enemy is attacked if the user hasn't clicked anything

  def updateNodes() =
    characterNodes.foreach(n => gameView.children -= n)
    characterNodes.empty
    setCharacters()

  // Maybe a bit inefficient and unnecessary to clear everything in updateNodes() and updateInfo()
  def updateInfo() =
    gameView.userPartyInfo.children.clear()
    gameView.enemyPartyInfo.children.clear()
    setTextInfo()

  def updateButtons() =
    val cTurn = game.characterTurn
    skill1Button.text = cTurn.skill1Name
    skill2Button.text = cTurn.skill2Name
    skill3Button.text = cTurn.skill3Name
    skill4Button.text = cTurn.skill4Name

  // Update method that updates everything
  def update() =
    game.update()
    updateNodes()
    updateInfo()
    if game.isOver then
      new Alert(AlertType.Information) {
        title = "Game Over!"
        headerText = game.winner
        contentText = "You are now going back to the menu screen."
      }.showAndWait()
      openView(gameMenu)
    else
      updateButtons()
      inTurn()

  // Set up button events
  skill1Button.onAction = (event) =>
    userParty.head.skill1(target)
    update()
  skill2Button.onAction = (event) =>
    userParty.head.skill2(target)
    update()
  skill3Button.onAction = (event) =>
    userParty.head.skill3(target)
    update()
  skill4Button.onAction = (event) =>
    userParty.head.skill4(target)
    update()

  this.root = gameMenu
  InputManager.handleInput(this)

  // Method to swap roots
  def openView(view: Parent) =
    this.root = view

  // Initial updates
  setTextInfo()
  updateButtons()
  setCharacters()
  val characterMap = characterNodes.zip(bothParties) // This was originally a map
  inTurn()
}
