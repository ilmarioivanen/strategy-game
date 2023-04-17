package game

import scala.collection.mutable.Buffer
import players._
import characters._
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
  val currentUser = new UserControlled(this)
  val currentEnemy = new EnemyAI(this)
  val currentPlayers = Buffer[Player](currentUser, currentEnemy)

  // add 3 Bobs to both parties for testing, new is mandatory for differentiating the objects
  for i <- 0 until 3 do
    currentPlayers.foreach(_.addToParty(new Bob))

  // These needed to be methods to update properly
  def ai = currentEnemy
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
  def startGame() = 
    gameStarted = true
  
  def update() =
    userLost = userParty.forall(_.isDead)
    userWon = aiParty.forall(_.isDead)
    gameOver = userLost || userWon
    val userDead = userParty.filter( c => c.isDead )
    val aiDead = aiParty.filter( c => c.isDead )
    userDead.foreach( d => currentUser.removeFromParty(d) )
    aiDead.foreach( d => currentEnemy.removeFromParty(d) )
    turnCount += 1
    // Automatically update game state if AI's turn and make the user wait
    if aiParty.contains(characterTurn) then
      val enemyTurn = Future {
        currentEnemy.takeTurn()
      }
      val turnDone = Await.result(enemyTurn, 10.second)
    else
      currentUser.takeTurn()
}
