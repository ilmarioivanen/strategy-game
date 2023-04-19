package stages

import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.Color.*

class Desert extends Stage("Desert") {

  override val background = Background(Array(new BackgroundFill((SandyBrown), CornerRadii.Empty, Insets.Empty)))
}
