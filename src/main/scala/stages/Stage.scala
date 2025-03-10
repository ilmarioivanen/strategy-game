package stages

import characters.*
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*

// Trait for defining stages
trait Stage(val name: String) {
  
  // Default background
  // As stage is a trait and abstrac this isn't currently used
  val background = Background(Array(new BackgroundFill((White), CornerRadii.Empty, Insets.Empty)))
  
  // method for stage effects, returns visual and description of what happened
  def effect(target: Character): (Node, String)
  
}