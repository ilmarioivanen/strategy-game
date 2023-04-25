package skills

import characters.*
import scalafx.scene.paint.Color.Black
import scalafx.scene.Node

trait Skill(

  val name: String,     // Skill name
  val attack: Boolean,  // True if skill is an attack
  val heals: Boolean,   // True if skill heals allies
  val negative: Boolean,// True if skill has negative effect on allies
  val nerfs: Boolean,   // True if skill has negative effect on enemies
  val mpCost: Int       // Mana cost of the skill
           
) {

  // Visual node or animation for the skill that could be shown by the gui
  val visual: Node
  
  // Method for calculating how much the skill user would deal damage
  // Needed so the ai can get the proper numbers
  // Needs to be abstract since the way damage is calcualted is different between skills
  def dmg(user: Character): Int 
  
  // Effect of the skill, returns the skill, user and target
  // If the visual is implemented then this would return it as well
  def effect(user: Character, target: Character): (Skill, Character, Character)
  
}
