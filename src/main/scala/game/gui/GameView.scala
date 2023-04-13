package game.gui

import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.Pane
import scalafx.geometry.Insets
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.VBox
import scalafx.scene.layout.HBox
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.layout.CornerRadii
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.RowConstraints
import scalafx.scene.control.Label
import scalafx.scene.paint.Color.*
import scalafx.geometry.*
//import java.awt
//import java.awt.{Component, Graphics, Label}
import javax.swing.border.Border
import players.UserControlled

class GameView extends GridPane {

    //Create button components
    val button1 = Button("Button1")
    button1.setMinSize(80, 40)
    val button2 = Button("Button2")
    button2.setMinSize(80, 40)
    val button3 = Button("Button3")
    button3.setMinSize(80, 40)
    val button4 = Button("Button4")
    button4.setMinSize(80, 40)

    //Create components to fill the grid box
    val buttons13 = new VBox {
      spacing = 10
      padding = Insets(10)
      children ++= List(button1, button3)
    }
    val buttons24 = new VBox {
      spacing = 10
      padding = Insets(10)
      children ++= List(button2, button4)
    }
    val bottomRight = new HBox {
      spacing = 5
      padding = Insets(0, 0, 0, 10)
      children = List(buttons13, buttons24)
    }
    val userPartyInfo = new VBox {
      padding = Insets(10, 10, 10, 10)
      minWidth = 100
      maxWidth = 150
      val character1 = Label("Bob")
      val character2 = Label("bob")
      val character3 = Label("obob")
      children = List(character1, character2, character3)
    }
    val enemyPartyInfo = new VBox {
      padding = Insets(10, 10, 10, 10)
      minWidth = 100
      maxWidth = 200
      val character1 = Label("Bob")
      val character2 = Label("bob")
      val character3 = Label("obob")
      children = List(character1, character2, character3)

    }

    val bottomLeft = new HBox {
      children = List(userPartyInfo, enemyPartyInfo)
    }

    //val canvas = new Canvas(600, 315)

    //Add child components to grid
    this.add(bottomRight, 1, 1)
    this.add(bottomLeft, 0, 1)
    //this.add(canvas, 0, 0, 2, 1)        // canvas probably needs to be replaced for different backgrounds

    //Define grid row and column size
    val column0 = new ColumnConstraints:
      percentWidth = 60
    val column1 = new ColumnConstraints:
      percentWidth = 40
    val row0 = new RowConstraints:
      percentHeight = 70
    val row1 = new RowConstraints:
      percentHeight = 30

    this.columnConstraints = Array[ColumnConstraints](column0, column1) //Add constraints in order
    this.rowConstraints = Array[RowConstraints](row0, row1)

    // set colors
    bottomRight.background = Background(Array(new BackgroundFill((White), CornerRadii.Empty, Insets.Empty)))
    bottomLeft.background = Background(Array(new BackgroundFill((White), CornerRadii.Empty, Insets.Empty)))

    // Borders with inline css since it was a lot easir this way than using setBorder with scalaFX
    bottomLeft.setStyle("-fx-border-width: 5; -fx-border-color: black")
    bottomRight.setStyle("-fx-border-width: 5; -fx-border-color: black")

}
