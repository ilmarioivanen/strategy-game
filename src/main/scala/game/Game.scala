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

  // Gamestate and players
  private var gameOver = false
  private var gameStarted = false
  private var userLost = false
  private var userWon = false
  private var turnCount = 0
  private var currentStage: Option[Stage] = None
  val currentUser = new UserControlled(this)
  val currentEnemy = new EnemyAI(this)
  val currentPlayers = Buffer[Player](currentUser, currentEnemy)
  val skillsInBattle = Buffer[Skill]()
  val stageVisuals = Buffer[Node]()


  // These needed to be methods to update properly
  def selectStage(stage: Stage) =
    currentStage = Some(stage)
  def stage = currentStage
  def userParty = currentUser.party
  def aiParty = currentEnemy.party
  def bothParties = userParty ++ aiParty
  def partySize = bothParties.size
  // Sort parties by speed
  def bySpeed = bothParties.sortBy(_.currentSpeed).reverse // fastest first
  def characterTurn =
    require(partySize > 0, "Party size cannot be negative!")
    bySpeed(turnCount % partySize)

  def isStarted = gameStarted
  def isOver = gameOver
  def winner: String =
    if userWon then 
      "You won!"
    else
      "You lost!"
  def endGame() =
    gameStarted = false
  def startGame() = 
    gameStarted = true
 
  
  def updateParties() = 
    val userDead = userParty.filter( c => c.isDead )
    val aiDead = aiParty.filter( c => c.isDead )
    userDead.foreach( d => currentUser.removeFromParty(d) )
    aiDead.foreach( d => currentEnemy.removeFromParty(d) )
    
  def stageEffect() =
    currentStage match
      case Some(stage) =>
        stageVisuals.clear()
        // select a random target
        val randomChar = shuffle(bothParties).head
        stageVisuals += stage.effect(randomChar)

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
