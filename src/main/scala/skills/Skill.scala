package skills

import characters._

trait Skill(

  val name: String,     // Skill name
  val attack: Boolean,  // True if skill is an attack
  val heals: Boolean,   // True if skill heals allies
  val negative: Boolean,// True if skill has negative effect on allies
  val nerfs: Boolean,   // True if skill has negative effect on enemies
  val instant: Boolean  // True if skill is instant and has no lingering effects

) {

  def effect(user: Character, target: Character): Int
  
}
