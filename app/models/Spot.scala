package models

/**
 * User: casey
 * Created: 9:02 PM 10/17/13
 */
class Spot extends Board[Option[Player]] {
  var here: Option[Player] = _
  val spots = None
  val players = None

  override def getWinner = here
  override def place(turn: Turn) { here = Some(turn.player) }
}
