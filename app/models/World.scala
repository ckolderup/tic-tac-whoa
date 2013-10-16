package models

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class World extends Board[Tile] {
  protected val spots = {
    val board = Array.ofDim[Tile](3, 3)
    for (i <- 0 until 3; j <- 0 until 3) board(i)(j) = new Tile
    board
  }

  def occupant(loc: Loc) = spots(loc.x)(loc.y).getWinner

  def place(turn: Turn, tile: Loc) {
    lastTurn.map(turn =>
      if (turn.loc != tile && !spots(turn.loc.x)(turn.loc.y).hasWinner) throw new InvalidPlacementException
    )

    spots(tile.x)(tile.y).place(turn)
    //TODO: don't do this one? already adding it in the Tile.place
    turns += Turn(turn.player, tile)
  }
}