package models

/**
 * Terminal Board child class to represent a spot with a player on it
 *
 * @author casey
 * @since 9:02 PM 10/17/13
 */
class Spot(val player: Option[Player]) extends Board {
  val spots = null

  override def players = player.toSet
  override def getWinner = player
  def place(turn: Turn): Spot = { new Spot(Some(turn.player)) }
}
