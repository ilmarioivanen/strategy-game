package game

import scala.collection.mutable.Buffer
import players._
import characters._
import stages._
import scala.concurrent.*
import scala.concurrent.duration.*
import ExecutionContext.Implicits.global
import scala.concurrent.Future

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


  // add random characters to the enemy party
  // originally for testing but turned out to be useful for avoiding errors and setting up the enemy party
  // bit of a band aid fix that could be avoided

  for i <- 0 until 3 do
    currentEnemy.addToParty(new Bob)

  // These needed to be methods to update properly
  def selectStage(stage: Stage) =
    currentStage = Some(stage)
  def stage = currentStage
  def userParty = currentUser.party
  def aiParty = currentEnemy.party
  def bothParties = userParty ++ aiParty
  def partySize = bothParties.size
  // Sort parties by speed
  def bySpeed = bothParties.sortBy(_.currentSpeed)
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
    // also checks if the AI is first in turn
    if aiParty.contains(bySpeed.head) then
      update()
  
  def update(): Unit =
    userLost = userParty.forall(_.isDead)
    userWon = aiParty.forall(_.isDead)
    gameOver = userLost || userWon
    val userDead = userParty.filter( c => c.isDead )
    val aiDead = aiParty.filter( c => c.isDead )
    userDead.foreach( d => currentUser.removeFromParty(d) )
    aiDead.foreach( d => currentEnemy.removeFromParty(d) )
    if !gameOver then
      turnCount += 1
      // Automatically update game state again if AI's turn and make the user wait
      if aiParty.contains(characterTurn) then
        val enemyTurn = Future {
          currentEnemy.takeTurn()
        }
        val turnDone = Await.result(enemyTurn, 100.second)
        update()

}
