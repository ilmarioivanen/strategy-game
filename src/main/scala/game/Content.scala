package game

import stages.*
import characters.*
import skills.*

// Last minute solution for loading a game file
// Tried to find a way to get all subclasses etc. since that would have helped a lot
class Content {

  val stages = Array(
    new Battleground,
    new Volcano,
    new Desert
  )

  // Characters and skills need to be methods since
  // the objects must be different every time the array is called

  def characters = Array(
    new Bob,
    new Gigachu,
    new Ranger,
    new Rogue,
    new Warrior,
    new Wizard
  )

  def skills = Array(
    new Barrage,
    new BattleCry,
    new CaffeineOverdose,
    new CalmMind,
    new Cripple,
    new Dodge,
    new Explosion,
    new Fireball,
    new Heal,
    new Poison,
    new QuickShot,
    new ShadowStrike,
    new Slash,
    new Thunder
  )

}
