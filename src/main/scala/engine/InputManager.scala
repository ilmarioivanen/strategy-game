package engine

import game.gui.GameGui
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.input.KeyCode
import scala.collection.mutable.Set

// Object to manage inputs such as Esc to go back to menu
// By default, the arrow keys and space bar can be used to navigate the buttons and click them
object InputManager {

  // Sets do not allow duplicates, so they are useful here
  val keysPressed = Set[KeyCode]()

  def handleInput(scene: GameGui) =

    scene.onKeyPressed = event =>
      keysPressed += event.code
      if event.code == KeyCode.Escape then scene.openMenu()

    // maybe need something for mouse movement
}
