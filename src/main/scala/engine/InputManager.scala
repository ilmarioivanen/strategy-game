package engine

import characters.Character // I'm no sure but I feel like having to import this is not ideal
import game.gui.GameGui
import scalafx.Includes._
import scalafx.scene.Scene
import scala.util.Random.shuffle
import scalafx.scene.input.KeyCode

// Object to manage inputs such as Esc to go back to menu
// This does NOT currently manage inputs given by clicking the gui buttons, only inputs from kb/mouse
// GUI button imputs could also be here but they directly alter the game state which is a bit different
// By default, the arrow keys and space bar can be used to navigate the buttons and click them
object InputManager {

  private var lastTargetOption: Option[Character] = None

  def lastTarget = lastTargetOption

  def handleInput(scene: GameGui) =

    scene.onKeyPressed = event =>
      if event.code == KeyCode.Escape then scene.openView(scene.gameMenu)

    scene.onMouseClicked = event =>
      val charMap = scene.characterMap
      val default = shuffle(charMap).head // The default option is a random character in play
      val node = event.getPickResult.getIntersectedNode
      val someTarget = charMap.find(_._1 == node)
      val targetNode = someTarget.getOrElse(default)._1
      val targetCharacter = someTarget.getOrElse(default)._2
      lastTargetOption = Some(targetCharacter)
      scene.targeted(targetNode)
}
