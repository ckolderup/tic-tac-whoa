package models

/**
 * Represents the overall ("outer") board
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class World extends Board {
  protected override val spots = {
    val board = Array.ofDim[Board](3, 3)
    for (i <- 0 until 3; j <- 0 until 3) board(i)(j) = new Tile
    board
  }

  def place(turn: Turn): World = {
    if (hasWinner) throw new TileCompleteException

    spots(turn.tileLoc.x)(turn.tileLoc.y).place(turn)
    this
  }
}
