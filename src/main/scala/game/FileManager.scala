package game

import players.*
import stages.*
import characters.*
import skills.*
import java.io.{FileNotFoundException, IOException}
import scala.collection.mutable.Buffer
import scala.util.{Failure, Success}
import scala.xml.*

// Class to save and load game files
class FileManager {

  // Method for saving a file
  def saveGame(filePath: String, game: Game) =

    try
      val gameFile =
        <game>
          <stage name = {game.stageName}></stage>
          <gamestate>
            <started>{game.isStarted}</started>
            <over>{game.isOver}</over>
            <userWon>{game.userWinner}</userWon>
            <turn>{game.turn}</turn>
          </gamestate>
          <players>
            <user>
              { for (character <- game.currentUser.party) yield
                <character name = {character.name}
                           hp = {character.currentHp.toString}
                           mp = {character.currentMp.toString}
                           atk = {character.currentAtk.toString}
                           mgc = {character.currentMgc.toString}
                           speed = {character.currentSpeed.toString}
                           usedTurn = {character.usedTurn.toString}>
              </character>
            }
            </user>
            <enemy>
              { for (character <- game.currentEnemy.party) yield
              <character name = {character.name}
                         hp = {character.currentHp.toString}
                         mp = {character.currentMp.toString}
                         atk = {character.currentAtk.toString}
                         mgc = {character.currentMgc.toString}
                         speed = {character.currentSpeed.toString}
                         usedTurn = {character.usedTurn.toString}>
              </character>
            }
            </enemy>
          </players>
        </game>

      XML.save(filePath, gameFile)

    // Handle possible exceptions  
    catch
      case _: FileNotFoundException => throw FileManagerException("Error with saving game data: Save file not found")
      case _: IOException => throw new FileManagerException("Error with saving game data: IOException")
      case _: Throwable => throw new FileManagerException("Error with saving game data: Unexpected exception.")


  // Helper variables and methods for the loadFile method
  val content = new Content
  val stages = content.allStages
  def characters = content.allCharacters
  def skills = content.allSkills
  // skills method was not used but it could be used to change characters' skills by editing the save files

  private def readStage(stageName: String) =
    val stage = stages.find(_.name == stageName)
    stage match
      case Some(s) => Success(s)
      case None => Failure(new FileManagerException("Reading stage data failed"))

  private def readChar(charName: String,
                       charHp: String,
                       charMp: String,
                       charAtk: String,
                       charMgc: String,
                       charSpeed: String,
                       usedTurn: String) = { // This looks hideous

    val char = characters.find(_.name == charName)
    char match
      case None => Failure(new FileManagerException("Reading character data failed"))
      case Some(c) =>
        try
          // Set proper stats and return the character
          c.setHp(charHp.toInt)
          c.setMp(charMp.toInt)
          c.setAtk(charAtk.toInt)
          c.setMgc(charMgc.toInt)
          c.setSpeed(charSpeed.toInt)
          c.setDone(usedTurn.toBoolean)
          Success(c)
        catch
          case _ => Failure(new FileManagerException("Reading character data failed"))
  }

  // Method for reading the save five and loading the game
  def loadGame(filePath: String, game: Game) =

    try
      val saveFile = XML.loadFile(filePath)

      // Variables for stage, characters
      var gameStage = stages.head
      val userCharacters = Buffer[Character]()
      val enemyCharacters = Buffer[Character]()

      // Read stage
      val stageName = (saveFile \ "stage" \ "@name").text
      readStage(stageName) match
        case Success(stage) => gameStage = stage
        case Failure(exception) => throw exception

      // Read and set up the game state
      try
        val started = (saveFile \ "gamestate" \ "started").text.toBoolean
        if started then game.startGame()

        val over = (saveFile \ "gamestate" \ "over").text.toBoolean
        if over then game.endGame()

        val userWon = (saveFile \ "gamestate" \ "userWon").text.toBoolean
        if userWon then game.userWin()

        val turn = (saveFile \ "gamestate" \ "turn").text.toInt
        game.setTurn(turn)

      catch
          case _ => throw new FileManagerException("Loading game state data failed")

      // Read the players and their characters
      // Players have no attributes themselves written to the save file
      try
        // Characters
        val userParty = (saveFile \ "players" \ "user" \ "character").foreach { character =>
          val name = (character \ "@name").text
          val hp = (character \ "@hp").text
          val mp = (character \ "@mp").text
          val atk = (character \ "@atk").text
          val mgc = (character \ "@mgc").text
          val speed = (character \ "@speed").text
          val done = (character \ "@usedTurn").text
          readChar(name, hp, mp, atk, mgc, speed, done) match
            case Success(character) => userCharacters += character
            case Failure(exception) => throw exception
        }
        // Fair amount of repetetion, not optimal
        val enemyParty = (saveFile \ "players" \ "enemy" \ "character").foreach { character =>
          val name = (character \ "@name").text
          val hp = (character \ "@hp").text
          val mp = (character \ "@mp").text
          val atk = (character \ "@atk").text
          val mgc = (character \ "@mgc").text
          val speed = (character \ "@speed").text
          val done = (character \ "@usedTurn").text
          readChar(name, hp, mp, atk, mgc, speed, done) match
            case Success(character) => enemyCharacters += character
            case Failure(exception) => throw exception
        }
        // Clear the remaining old data and set the new one
        game.reset()
        // Clearing skill/effect buffers with reset() is not optimal since it loses gui elements
        // Ideally I would've saved these to the save file but reading these was a pain since they
        // contain tuples of characters, skills and scalafx nodes
        game.selectStage(gameStage)
        userCharacters.foreach( c => game.currentUser.addToParty(c))
        enemyCharacters.foreach( c => game.currentEnemy.addToParty(c))

      catch
        case _ => throw new FileManagerException("Loading player data failed")

    catch
      case _: FileNotFoundException => throw FileManagerException("Error with loading game data: Save file not found")
      case _: IOException => throw new FileManagerException("Error with loading game data: IOException")

}

// Custom exception class
class FileManagerException(msg: String) extends Exception(msg)