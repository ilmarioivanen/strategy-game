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
  
  def dmg(user: Character) = 
    (user.baseAtkDmg * 0.2).toInt
  
  def effect(user: Character, target: Character) =
    user.endTurn()
    target.takeDamage(dmg(user))
}
