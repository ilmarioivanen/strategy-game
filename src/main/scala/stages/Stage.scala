package stages

import characters.*
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*


trait Stage(val name: String) {
  
  // default background
  val background = Background(Array(new BackgroundFill((White), CornerRadii.Empty, Insets.Empty)))
  
  // method for stage effects, returns node to show in gui
  def effect(target: Character): Node
  
}

