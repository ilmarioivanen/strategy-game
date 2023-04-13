package game

import scala.collection.mutable.Buffer
import players._
import characters._
import scala.util.Random.shuffle

class Game {

  private var gameOver = false
  private val currentUser = new UserControlled
  private val currentEnemy = new EnemyAI
  private val currentPlayers = shuffle(Buffer[Player](currentUser, currentEnemy)) // randomizes who makes the first move
  private val userParty = currentUser.party
  private val aiParty = currentEnemy.party

  // add Bob to both parties for testing
  currentPlayers.foreach(_.addToParty(Bob))

  def user = currentUser
  def ai = currentEnemy
  def players = currentPlayers
  def isOver = gameOver

}
