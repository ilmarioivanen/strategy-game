package game.gui

import scala.collection.mutable.Buffer
import scalafx.Includes.*
import game.Game
import scalafx.scene.{Group, Node, Parent, Scene}
import scalafx.scene.text.Font
import scalafx.scene.control.Label
import engine.InputManager
import javafx.scene.shape.Circle
import scalafx.geometry.Bounds
import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color.*
import scalafx.scene.effect
import scalafx.scene.control.Alert.*
import scalafx.scene.control.Alert
import scalafx.scene.paint.Color

// The class was made for switching between normal gameview and the menu.
// The class got super crowded since all of the buttons have actions that alter the game state

class GameGui(game: Game) extends Scene {

  // Set up info from game
  val user = game.currentUser
  val enemy = game.currentEnemy
  val userParty = game.userParty
  val enemyParty = game.aiParty
  val bothParties = game.bothParties
  // Set up sprites
  val characterNodes = Buffer[Node]()

  // Set up the base gameview
  val gameView = new GameView

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
    // needs to change for multiple stages
    // also a message if no stage is chosen
    stage1.onAction = (event) =>
      gameView.setBackground(this.bg.background)
      this.part2.text = bg.name
    next.onAction = (event) => openView(characterSelect)
  }

  val characterSelect = new CharacterSelect {

    // actions for selecting your party
    // also remember to update the label

    clear.onAction = (event) =>
      userParty.clear()

    startGame.onAction = (event) =>
      if game.userParty.isEmpty then
        this.messages.text = "Please add at least one character to your party."
      else
        openView(gameView)
        game.startGame()
        // Initial updates
        updateInfo()
        updateButtons()
        updateNodes()
        inTurn()
  }

  // Set up stages?


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
    val nodeInTurn = characterMap.map((n, c) => (c, n))
      .toMap
      .get(game.characterTurn)
    nodeInTurn match
      case Some(node) =>
        node.effect = turnEffect
      case None => // some error since some character should always be in turn

  def targeted(targetNode: Node) =
    // reset old target effects
    gameView.children.foreach(_.setEffect(null))
    inTurn() // calls inTurn() to reset turn effect in case it was removed
    val targetEffect = new DropShadow {
                             color = Red
                             radius = 30
                             spread = 0.75
    }
    targetNode.effect = targetEffect
    lastTargetNode = targetNode

  def target =
    InputManager.lastTarget match
      case Some(character) =>
        // If-else so there's a new target in case the last target died or is not in either party (aka old info)
        if character.isDead || !bothParties.contains(character) then
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
      game.endGame()
    else
      updateButtons()
      inTurn()
      targeted(lastTargetNode)


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
  InputManager.handleInput(this) // This could be placed elsewhere to avoid inputs when there aren't targets for example

  // Method to swap roots
  def openView(view: Parent) =
    this.root = view

  // Initial updates to avoid errors due to missing targets
  // Probably should've implemented this better
  updateNodes()
  val characterMap = characterNodes.zip(bothParties) // This was originally a map.
  private var lastTargetNode = characterNodes.head

}
