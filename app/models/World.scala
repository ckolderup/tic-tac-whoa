package models

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 11:53 AM
 */

class World extends Board[Tile] {
  protected val spots = mkArray

  def occupant(loc: Loc) = spots(loc.x)(loc.y).getWinner

  def place(turn: Turn, tile: Loc) {
    if (lastTurn.loc != tile && !spots(lastTurn.loc.x)(lastTurn.loc.y).hasWinner) throw new InvalidPlacementException

    spots(tile.x)(tile.y).place(turn)
    turns += Turn(turn.player, tile)
  }
}
