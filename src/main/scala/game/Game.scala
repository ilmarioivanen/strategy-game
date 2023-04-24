package game

import scala.collection.mutable.Buffer
import players.*
import characters.*
import skills.Skill
import stages.*
import scalafx.scene.Node
import scala.util.Random
import scala.util.Random.*

class Game {

  // File manager
  val fileManager = new FileManager

  // Variables for game state etc.
  private var gameOver = false
  private var gameStarted = false
  private var userLost = false
  private var userWon = false
  private var turnCount = 0
  private var currentStage: Option[Stage] = None
  val currentUser = new UserControlled(this)
  val currentEnemy = new EnemyAI(this)
  val currentPlayers = Buffer[Player](currentUser, currentEnemy)
  val skillsInBattle = Buffer[(Skill, Character, Character)]()
  val stageEffects = Buffer[(Node, String)]()

  // Methods for game state etc.
  def selectStage(stage: Stage) =
    currentStage = Some(stage)
  def stage = currentStage
  def stageName =
    currentStage match
      case Some(stage) => stage.name
      case None => "None"
  def userParty = currentUser.party
  def aiParty = currentEnemy.party
  def getSkills = skillsInBattle
  def getEffects = stageEffects
  def bothParties = userParty ++ aiParty
  def partySize = bothParties.size
  // Sort parties by speed
  def bySpeed = bothParties.sortBy(_.currentSpeed).reverse // fastest first
  def turn = turnCount
  def characterTurn =
    require(partySize > 0, "Party size cannot be negative!")
    bySpeed(turnCount % partySize)

  def isStarted = gameStarted
  def isOver = gameOver
  def userWinner = userWon
  def winnerText: String =
    if userWon then 
      "You won!"
    else
      "You lost!"

  def setTurn(turn: Int) =
    turnCount = turn
  def endGame() =
    gameStarted = false
  def startGame() = 
    gameStarted = true
  def userWin() =
    userWon = true


  // Update methods

  def updateParties() = 
    val userDead = userParty.filter( c => c.isDead )
    val aiDead = aiParty.filter( c => c.isDead )
    userDead.foreach( d => currentUser.removeFromParty(d) )
    aiDead.foreach( d => currentEnemy.removeFromParty(d) )
    
  def stageEffect() =
    currentStage match
      case Some(stage) =>
        stageEffects.clear()
        // effect happens randomly
        val chance = 50
        val random = Random(System.nanoTime())
        val rNum = random.nextInt(100)
        if rNum <= chance then
          // select a random target every turn
          val randomChar = shuffle(bothParties).head
          stageEffects += stage.effect(randomChar)

      case None => println("Stage is missing")

    
  def update(): Unit =
    userLost = userParty.forall(_.isDead)
    userWon = aiParty.forall(_.isDead) && !userLost
    gameOver = userLost || userWon
    updateParties()
    stageEffect()
    if !gameOver then
      turnCount += 1
      // Update game state again if AI's turn and make the user wait
      if aiParty.contains(characterTurn) then
        currentEnemy.takeTurn()
        update()
}
