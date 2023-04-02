import scalafx.Includes.*
import scalafx.application.JFXApp3
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
import scalafx.scene.paint.Color.*
import scalafx.geometry._
import scalafx.scene.text.Font
import java.awt
import java.awt.{Component, Graphics}
import javax.swing.border.Border

object GameApp extends JFXApp3:

  val game = new Game


  def start(): Unit =


    stage = new JFXApp3.PrimaryStage:
      title = "Hello Stage"
      width = 600
      height = 450

    /*
    Create root gui component, add it to a Scene
    and set the current window scene.
    */


    // Needs to be altered for multiple stages

    val root = GridPane() // GridPane component
    val scene = Scene(parent = root) // Scene acts as a container for the scene graph
    stage.scene = scene // Assigning the new scene as the current scene for the stage

    //Create button components
    val button1 = Button("Button1")
    button1.setMinSize(80, 40)
    val button2 = Button("Button2")
    button2.setMinSize(80, 40)
    val button3 = Button("Button3")
    button3.setMinSize(80, 40)
    val button4 = Button("Button4")
    button4.setMinSize(80, 40)

    //Set button actions
    button1.onAction = (event) => println("button1")
    button2.onAction = (event) => println("button2")
    button3.onAction = (event) => println("button3")
    button4.onAction = (event) => println("button4")

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
    val bottomLeft = new HBox
    val canvas = new Canvas(600, 315)

    //Add child components to grid
    root.add(bottomRight, 1, 1)
    root.add(bottomLeft, 0, 1)
    root.add(canvas, 0, 0, 2, 1)

    //Define grid row and column size
    val column0 = new ColumnConstraints:
      percentWidth = 60
    val column1 = new ColumnConstraints:
      percentWidth = 40
    val row0 = new RowConstraints:
      percentHeight = 70
    val row1 = new RowConstraints:
      percentHeight = 30

    root.columnConstraints = Array[ColumnConstraints](column0, column1) //Add constraints in order
    root.rowConstraints = Array[RowConstraints](row0, row1)

    // set colors
    bottomRight.background = Background(Array(new BackgroundFill((White), CornerRadii.Empty, Insets.Empty)))
    bottomLeft.background = Background(Array(new BackgroundFill((White), CornerRadii.Empty, Insets.Empty)))

    // Borders with inline css since it was a lot easir this way than using setBorder with scalaFX
    bottomLeft.setStyle("-fx-border-width: 5; -fx-border-color: black")
    bottomRight.setStyle("-fx-border-width: 5; -fx-border-color: black")


