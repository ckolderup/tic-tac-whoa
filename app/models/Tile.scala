package models

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class TileCompleteException extends Exception

class Tile extends Board[Option[Player]] {
  protected val spots = mkArray

  def occupies(player: Player, loc: Loc) = spots(loc.x)(loc.y) == Some(player)

  def place(turn: Turn) {
    if (hasWinner) throw new TileCompleteException
    if (spots(turn.loc.x)(turn.loc.y).isDefined) throw new InvalidPlacementException

    spots(turn.loc.x)(turn.loc.y) = Some(turn.player)
    turns += turn
  }
}