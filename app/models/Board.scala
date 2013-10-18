package models

/**
 * Generic class to represent an NxN board of something
 *
 * @author ckolderup
 * @since 6/20/13 9:59 AM
 */
class InvalidPlacementException extends Exception
class TileCompleteException extends InvalidPlacementException

trait Board[T<:Board] {
  def hasWinner: Boolean = getWinner.isDefined

  def spot(loc: Loc) = spots(loc.x)(loc.y)

  def occupant(loc: Loc): Option[Player] = spots(loc.x)(loc.y).getWinner

  def owns(player: Player, line: Line): Boolean = line.locs.forall(l => occupant(l) == Some(player))

  def getWinner: Option[Player] = {
    players.collectFirst { case player =>
    Line.all.map(line => owns(player, line)) match {
        case true => player
      }
    }
  }

  def place(turn: Turn) {
    if (hasWinner) throw new TileCompleteException

    spots(turn.tileLoc.x)(turn.tileLoc.y).place(turn)
  }

  protected val players: Set[Player]
  protected val spots: Array[Array[T]]
}
