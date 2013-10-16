package models

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class TileCompleteException extends Exception

//TODO: make it a board of some class that encapsulates Option[Player] so that more code can be shared between World and Tile via a mixin trait
class Tile extends Board[Option[Player]] {
  //TODO: abstractable? or does the ofDim stop that still?
  protected val spots = {
    val board = Array.ofDim[Option[Player]](3, 3)
    for (i <- 0 until 3; j <- 0 until 3) board(i)(j) = None
    board
  }

  //TODO: should be abstractable to match World.occupant
  def occupant(loc: Loc) = spots(loc.x)(loc.y)

  def place(turn: Turn) {
    if (hasWinner) throw new TileCompleteException
    if (spots(turn.loc.x)(turn.loc.y).isDefined) throw new InvalidPlacementException

    spots(turn.loc.x)(turn.loc.y) = Some(turn.player)
    turns += turn
  }
}
