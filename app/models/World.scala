package models

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class World(val players: Set[Player]) extends Board[Tile] {
  protected val spots = {
    val board = Array.ofDim[Tile](3, 3)
    for (i <- 0 until 3; j <- 0 until 3) board(i)(j) = new Tile(players)
    board
  }
}
