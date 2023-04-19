package stages

import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*


trait Stage(val name: String) {
  
  // default background
  val background = Background(Array(new BackgroundFill((White), CornerRadii.Empty, Insets.Empty)))

  
}
