package models

/**
 * Generic class to represent an NxN board of something
 *
 * @author ckolderup
 * @since 6/20/13 9:59 AM
 */
class InvalidPlacementException extends Exception
class TileCompleteException extends InvalidPlacementException

trait Board {
  def hasWinner: Boolean = getWinner.isDefined

  def spot(loc: Loc) = spots(loc.x)(loc.y)

  def occupant(loc: Loc): Option[Player] = spots(loc.x)(loc.y).getWinner

  def owns(player: Player, line: Line): Boolean = line.locs.forall(l => occupant(l) == Some(player))

  def getWinner: Option[Player] = {
    players.collectFirst { case player =>
      Line.all.collectFirst { case line =>
        owns(player, line)
      } match {
        case Some(true) => player
        case _ => null
      }
    }
  }

  def players: Set[Player] = spots.flatMap(_.flatMap(_.players)).toSet

  def place(turn: Turn): Board
  protected val spots: Array[Array[Board]]
}
