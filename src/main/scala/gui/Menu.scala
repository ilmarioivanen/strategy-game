package gui

import scalafx.scene.control.Button
import scalafx.scene.layout.VBox

class Menu extends VBox {

  val startButton = new Button("bbb") {
    onAction = (event) => println("dunno")
  }
  
  this.children = startButton
}
