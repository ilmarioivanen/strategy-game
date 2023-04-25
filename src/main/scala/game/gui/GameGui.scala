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
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.effect
import scalafx.scene.control.Alert.*
import scalafx.scene.control.Alert
import scala.util.Random.shuffle
import scalafx.animation.*
import scalafx.geometry.Pos.*

// The class was made for switching between normal gameview and the menu.
// The class got super crowded since all of the buttons have actions that alter the game state
// Some of the code here should have and probably could have been placed to elsewhere (other gui classes etc.)
class GameGui(game: Game) extends Scene {

  // Set up info from game
  val gui = this
  val user = game.currentUser
  val enemy = game.currentEnemy
  val characterNodes = Buffer[Node]()
  private var lastTargetNode: Node = new Rectangle // Some random rectangle as initial value

  // These needed to be a method to function properly
  // Parties can change during gameplay and outside of gameplay if new game is started or old save file loaded
  def userParty = game.userParty
  def enemyParty = game.aiParty
  def bothParties = game.bothParties

  // Set up the base gameview
  val gameView = new GameView

  // Set up the main menu
  val gameMenu: Menu = new Menu {

    continue.onAction = (event) =>
      if game.isStarted then
        openView(gameView)
        // Gui needs to be initially updated if save file is loaded
        updateGui()
        this.messages.text = ""
      else
        this.messages.text = "There's no game to continue. Please start a new game or load a save."

    newGame.onAction = (event) =>
      // End the previous game and reset the game state and nodes
      game.endGame()
      game.reset()
      openView(stageSelect)
      this.messages.text = ""

    // Actions when saving/loading the game
    // Errors are caught here if something failed and an alert is shown
    saveGame.onAction = (event) =>
      try
        game.fileManager.saveGame("src/main/savefiles/save1.xml", game)
      catch
        case e: Throwable => errorAlert("Saving the game failed.", e)

    loadGame.onAction = (event) =>
      try
        game.fileManager.loadGame("src/main/savefiles/save1.xml", game)
      catch
        case e: Throwable => errorAlert("Loading the game failed.", e)
  }

  // Set up the stage select screen
  val stageSelect = new StageSelect {
    
    for stage <- this.stagesToButtons do
      stage._2.onAction = (event) =>
        game.selectStage(stage._1)
        this.part2.text = stage._1.name
        
    this.next.onAction = (event) =>
      game.stage match
        case Some(stage) =>
          openView(characterSelect)
          this.part2.text = ""
        case None =>
          this.messages.text = "Please select a stage before continuing."
  }

  // Set up the character select screen
  // This was changed to method to avoid calling identical or old character objects
  // which happened if the old characterSelect was used after eg. winning a game
  def characterSelect = new CharacterSelect {

    // Each button is linked with a (character, character, character)-tuple of same character classes
    // This way picking a character adds a different instance of the class to user's party
    for character <- this.charToButtons do
      character._2.onAction = (event) =>
        val char =
          if userParty.contains(character._1._1) then
            if userParty.contains(character._1._2) then
              character._1._3
            else
              character._1._2
          else
            character._1._1
        user.addToParty(char)
        this.part2.text = game.userParty.map(_.name).mkString(", ")

    clear.onAction = (event) =>
      userParty.clear()
      this.part2.text = ""

    startGame.onAction = (event) =>
      if game.userParty.isEmpty then
        this.messages.text = "Please add at least one character to your party."
      else
        this.part2.text = ""
        // Start the game
        openView(gameView)
        game.startGame()
        // Add characters to enemy party so party sizes match
        for n <- userParty.indices do
          val enemyChar = shuffle(content.allCharacters).head
          enemy.addToParty(enemyChar)
        // Check if the AI is first in turn
        // if yes then the enemy takes the first turn and then update everything
        // else update only the gui for the user
        if enemyParty.contains(game.characterTurn) then
          enemy.takeTurn()
          update()
        else
          updateGui()
  }

  // Set up buttons
  val skill1Button = gameView.button1
  val skill2Button = gameView.button2
  val skill3Button = gameView.button3
  val skill4Button = gameView.button4

  // Set up button events
  skill1Button.onAction = (event) =>
    game.skillsInBattle += game.characterTurn.skill1(target)
    update()
  skill2Button.onAction = (event) =>
    game.skillsInBattle += game.characterTurn.skill2(target)
    update()
  skill3Button.onAction = (event) =>
    game.skillsInBattle += game.characterTurn.skill3(target)
    update()
  skill4Button.onAction = (event) =>
    game.skillsInBattle += game.characterTurn.skill4(target)
    update()

  // This was originally a map.
  // Creates an array of nodes and their respective characters in tuples
  def characterMap =
    characterNodes.zip(bothParties)

  // Sets up text info
  def setTextInfo() =
    val userInfo = gameView.userPartyInfo
    val enemyInfo = gameView.enemyPartyInfo
    userInfo.children += Label("Your party:")
    enemyInfo.children += Label("Enemy party:")
    for character <- userParty do
      userInfo.children += Label(s"${character.name}:   ${character.currentHp} HP    ${character.currentMp} MP")
    for character <- enemyParty do
      enemyInfo.children += Label(s"${character.name}:   ${character.currentHp} HP    ${character.currentMp} MP")

  // Sets up position of characters' sprites/nodes
  // Positions are constant for now
  def setCharacters() =
    for character <- userParty do
      val sprite = character.sprite 
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

  // Sets an effect to a node to tell who's turn it is
  def inTurn() =
    // reset old turn effects
    gameView.children.foreach(_.setEffect(null))
    val turnEffect = new DropShadow {
                           color = Black
                           radius = 20
                           spread = 0.5
    }
    // Only create effect if possible
    if bothParties.nonEmpty then
      val nodeInTurn = characterMap.map((n, c) => (c, n))
        .toMap
        .get(game.characterTurn)
      nodeInTurn match
        case Some(node) =>
          node.effect = turnEffect
        case None => // This can happen when every node disappears at the same time and isn't an exception

  // Sets an effect to a node to tell who's targeted
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

  // Gets current target given by InputManager
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

  // Shows an alert when game is over
  def gameOverAlert =
    new Alert(AlertType.Information) {
        title = "Game Over!"
        headerText = game.winnerText
        contentText = "You are now going back to the menu screen."
      }.showAndWait()

  // Method for showing file manager errors as alerts
  def errorAlert(msg: String, exception: Throwable) =
    new Alert(AlertType.Error) {
      title = "Error"
      headerText = msg
      contentText = exception.toString
      exception.printStackTrace() // Print exception to console.
    }.showAndWait()

  // Updates the background of the gameView
  def updateBackground() =
    game.stage match
      case Some(stage) => gameView.setBackground(stage.background)
      case None => println("No stage found")

  // Updates the nodes in gameView
  def updateNodes() =
    characterNodes.foreach(n => gameView.children -= n)
    characterNodes.clear()
    setCharacters()

  // Updates the text info of characters' and their stats
  def updateInfo() =
    gameView.userPartyInfo.children.clear()
    gameView.enemyPartyInfo.children.clear()
    setTextInfo()

  // Updates buttons' texts to match the character's skills whos in turn
  def updateButtons() =
    // Only update buttons if possible
    if bothParties.nonEmpty then
      val cTurn = game.characterTurn
      skill1Button.text = cTurn.skill1Name
      skill2Button.text = cTurn.skill2Name
      skill3Button.text = cTurn.skill3Name
      skill4Button.text = cTurn.skill4Name

  // skillInfo and updateSkills() are last minute solution that shows text descriptions of skills
  // also slapped last minute stage effects (descriptions) here
  // Planned to use the method for updating/animating the visual skill effects
  val skillInfo = new Label("")
  skillInfo.translateX = 200
  skillInfo.translateY = 80
  gameView.children += skillInfo

  def updateSkills() =
    var allInfo = ""
    // Stage effects
    for effect <- game.stageEffects do
      val description = effect._2
      allInfo += description + "\n"
    // Skills
    for skill <- game.skillsInBattle do
      val skillName = skill._1.name
      val user = skill._2.name
      val target = skill._3.name
      allInfo += s"$user used $skillName on $target!\n"
    skillInfo.text = allInfo
    game.skillsInBattle.clear()

  // Update method that updates the gui elements
  def updateGui() =
    updateBackground()
    updateSkills()
    updateInfo()
    updateButtons()
    updateNodes()
    inTurn()

  // Update method that updates everything and checks if game is over
  def update() =
    game.update()
    updateGui()
    if game.isOver then
      gameOverAlert
      openView(gameMenu)
      game.endGame()
    else
      targeted(lastTargetNode)

  // Method to swap roots
  def openView(view: Parent) =
    this.root = view

  // Initially set root to gameMenu and call the handleInput method for gui
  this.root = gameMenu
  InputManager.handleInput(gui)

}