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

  def place(turn: Turn, loc: Loc) {
    if (lastTurn.loc != loc && !spots(lastTurn.loc.x)(lastTurn.loc.y).hasWinner) throw new InvalidPlacementException

    spots(loc.x)(loc.y).place(turn)
  }
}