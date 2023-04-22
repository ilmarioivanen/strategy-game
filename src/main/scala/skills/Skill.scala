package skills

import characters.*
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.Shape

trait Skill(

  val name: String,     // Skill name
  val attack: Boolean,  // True if skill is an attack
  val heals: Boolean,   // True if skill heals allies
  val negative: Boolean,// True if skill has negative effect on allies
  val nerfs: Boolean,   // True if skill has negative effect on enemies
  val instant: Boolean, // True if skill has an instant effect
  val turns: Int        // How many turns the skill affect, 0 if none after casting

) {

  
  val visual: Shape
  
  // Method for calculating how much the skill user would deal damage
  // Needed so the ai can get the proper numbers
  def dmg(user: Character): Int 
  
  // Effect of the skill, returns the skill itself for later use
  def effect(user: Character, target: Character): Skill
  
}
