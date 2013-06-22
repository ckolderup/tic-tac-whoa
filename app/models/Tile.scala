package models

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class TileCompleteException extends Exception

class Tile extends Board[Option[Player]] {
  protected val spots = {
    val board = Array.ofDim[Option[Player]](3, 3)
    for (i <- 0 until 3; j <- 0 until 3) board(i)(j) = None
    board
  }

  def occupant(loc: Loc) = spots(loc.x)(loc.y)

  def place(turn: Turn) {
    if (hasWinner) throw new TileCompleteException
    if (spots(turn.loc.x)(turn.loc.y).isDefined) throw new InvalidPlacementException

    spots(turn.loc.x)(turn.loc.y) = Some(turn.player)
    turns += turn
  }
}
