package gui

import game.Game
import scalafx.scene.Scene

class GameGui(game: Game) extends Scene {

  val gameMenu = new Menu
  val gameView = new GameView
  
  this.root = gameView

}
