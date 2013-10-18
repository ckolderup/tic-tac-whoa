package models

/**
 * Represents the individual, playable ("inner") boards
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class Tile(val players: Set[Player]) extends Board[Spot] {
  protected val spots = {
    val board = Array.ofDim[Spot](3, 3)
    for (i <- 0 until 3; j <- 0 until 3) board(i)(j) = new Spot
    board
  }
}
