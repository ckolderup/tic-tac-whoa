package models

/**
 * A location on a board.
 * Companion object provides all of the locations for a 3x3 board.
 *
 * @author ckolderup
 * @since 6/20/13 10:29 AM
 */
object Loc {
  val TOP_LEFT: Loc = Loc(0, 0)
  val TOP_MIDDLE: Loc = Loc(0, 1)
  val TOP_RIGHT: Loc = Loc(0, 2)
  val MIDDLE_LEFT: Loc = Loc(1, 0)
  val MIDDLE: Loc = Loc(1, 1)
  val MIDDLE_RIGHT: Loc = Loc(1, 2)
  val BOTTOM_LEFT: Loc = Loc(2, 0)
  val BOTTOM_MIDDLE: Loc = Loc(2, 1)
  val BOTTOM_RIGHT: Loc = Loc(2, 2)

  def all = Set(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT,
                MIDDLE_LEFT, MIDDLE, MIDDLE_RIGHT,
                BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT)

}

case class Loc(x: Int, y: Int) {
  def lines = Line.has(this)
}
