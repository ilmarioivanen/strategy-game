package engine

import characters.Character
import game.gui.GameGui
import scalafx.Includes._
import scalafx.scene.Scene
import scala.util.Random.shuffle
import scalafx.scene.input.KeyCode

// InputManager is an Object to manage inputs such as Esc to go back to menu
// This does NOT currently manage inputs given by clicking the gui buttons, only inputs from kb/mouse
// GUI button imputs could also be here but they directly alter the game state which is a bit different
// By default, the arrow keys and space bar can be used to navigate the buttons and click them
object InputManager {

  // Variable to keep track of last target
  // Ideally this would be placed elsewhere to keep game variables separate from InputManager
  private var lastTargetOption: Option[Character] = None

  def lastTarget = lastTargetOption
  
  // Method to handle keyboard and mouse inputs
  // Should've maybe split this to two different methods as this one method caused some problems
  def handleInput(scene: GameGui): Unit =

    // Opens menu with Esc
    scene.onKeyPressed = event =>
      if event.code == KeyCode.Escape then scene.openView(scene.gameMenu)

    // Selects a target by clicking on Nodes on gui if possible
    scene.onMouseClicked = event =>
      val charMap = scene.characterMap // Contains (node, character)-tuples
      if charMap.nonEmpty then
        val default = shuffle(charMap).head // The default option is a random character in play
        val node = event.getPickResult.getIntersectedNode
        val someTarget = charMap.find(_._1 == node)
        val targetNode = someTarget.getOrElse(default)._1
        val targetCharacter = someTarget.getOrElse(default)._2
        lastTargetOption = Some(targetCharacter)
        scene.targeted(targetNode) // Sets the targeting effect
}
