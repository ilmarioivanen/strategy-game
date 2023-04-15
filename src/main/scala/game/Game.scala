package game

import scala.collection.mutable.Buffer
import players._
import characters._
import scala.util.Random.shuffle

class Game {

  private var gameOver = false
  private var gameStarted = false
  private val currentUser = new UserControlled
  private val currentEnemy = new EnemyAI
  private val currentPlayers = shuffle(Buffer[Player](currentUser, currentEnemy)) // randomizes who makes the first move
  private val userParty = currentUser.party
  private val aiParty = currentEnemy.party

  // add 3 Bobs to both parties for testing, new is mandatory for differentiating the objects
  for i <- 0 until 3 do 
    currentPlayers.foreach(_.addToParty(new Bob))

  def isStarted =
    gameStarted
  def startGame() = 
    gameStarted = true
    
  def user = currentUser
  def ai = currentEnemy
  def players = currentPlayers
  def isOver = gameOver

}
