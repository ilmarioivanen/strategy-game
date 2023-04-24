package game

import players.*
import stages.*
import characters.*
import skills.*
import scala.collection.mutable.Buffer
import scala.util.{Failure, Success}
import scala.xml.*


class FileManager {


  def saveGame(fileName: String, game: Game) =


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
                           speed = {character.currentSpeed.toString}>
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
                         speed = {character.currentSpeed.toString}>
              </character>
            }
            </enemy>
          </players>
        </game>

      XML.save("src/main/savefiles/"+fileName+".xml", gameFile)

    catch
      case _ => throw new FileManagerException("Saving the game failed")


  // Helper methods etc. for the load method
  val content = new Content
  val stages = content.stages
  def characters = content.characters
  def skills = content.skills
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
                       charSpeed: String) = { // Brackets for clarity

    val char = characters.find(_.name == charName)
    char match
      case None => Failure(new FileManagerException("Reading character data failed"))
      case Some(c) =>
        try
          // Set proper stats
          c.setHp(charHp.toInt)
          c.setMp(charMp.toInt)
          c.setAtk(charAtk.toInt)
          c.setMgc(charMgc.toInt)
          c.setSpeed(charSpeed.toInt)
          Success(c)
        catch
          case _ => Failure(new FileManagerException("Reading character data failed"))
  }


  // Method for reading the save five and loading the game
  def loadGame(fileName: String, game: Game) =

    val saveFile = XML.loadFile("src/main/savefiles/"+fileName+".xml")

    // Buffers for characters
    val userCharacters = Buffer[Character]()
    val enemyCharacters = Buffer[Character]()

    // Read stage
    val stageName = (saveFile \ "stage" \ "@name").text
    readStage(stageName) match
      case Success(stage) => game.selectStage(stage)
      case Failure(exception) => throw exception

    // Read game state
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
    // players have no attributes themselves
    try
      // Characters
      val userParty = (saveFile \ "players" \ "user" \ "character").foreach { character =>
        val name = (character \ "@name").text
        val hp = (character \ "@hp").text
        val mp = (character \ "@mp").text
        val atk = (character \ "@atk").text
        val mgc = (character \ "@mgc").text
        val speed = (character \ "@speed").text
        readChar(name, hp, mp, atk, mgc, speed) match
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
        readChar(name, hp, mp, atk, mgc, speed) match
          case Success(character) => enemyCharacters += character
          case Failure(exception) => throw exception
      }
      // Clear the old data and set the new one
      // Stage was already set before ...
      // which might mean that the stage loads but characters don't in some error situations
      game.userParty.clear()
      game.aiParty.clear()
      // Clearing these skill/effect buffers is not optimal since it might affect the gameplay
      // Ideally I would've saved these to the save file but reading these was a pain since they
      // contain tuples of characters and skills
      game.skillsInBattle.clear()
      game.stageEffects.clear()
      userCharacters.foreach( c => game.currentUser.addToParty(c))
      enemyCharacters.foreach( c => game.currentEnemy.addToParty(c))

    catch
      case _ => throw new FileManagerException("Loading player data failed")

}

// Custom exception class
class FileManagerException(msg: String) extends Exception(msg)
