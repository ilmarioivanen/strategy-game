import game.Game
import game.gui.GameGui
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.Includes._

object GameApp extends JFXApp3:

  def start() =

    val game = new Game
    val gui = GameGui(game)
  
    stage = new JFXApp3.PrimaryStage:
      title.value = "Strategy Game"
      width = 600
      height = 450
      scene = gui
      resizable = false