package skills

import characters._

class Slash extends Skill(
  
  name = "Slash",
  attack = true,
  heals = false,
  negative = false,
  nerfs = false,
  instant = true
  
) {
  
  def effect(user: Character, target: Character) =
    val dmg = (user.baseAtkDmg * 0.2).toInt
    target.endTurn()
    target.takeDamage(dmg)
}
