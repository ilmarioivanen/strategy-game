import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.Pane

object GameApp extends JFXApp3:

  def start(): Unit =

    /*
    Creation of a new primary stage (Application window).
    We can use Scala's anonymous subclass syntax to get quite
    readable code.
    */

    stage = new JFXApp3.PrimaryStage:
      title = "Hello Stage"
      width = 600
      height = 450

    /*
    Create root gui component, add it to a Scene
    and set the current window scene.
    */
    val root = Pane() // Simple pane component
    val scene = Scene(parent = root) // Scene acts as a container for the scene graph
    stage.scene = scene // Assigning the new scene as the current scene for the stage


    //Create button component
    val button = Button("I'm a button!")

    //Set button action
    button.onAction = (event) => println("Click!")

    //Add button to the GUI.
    root.children += button //Needs scalafx.Includes._ import
