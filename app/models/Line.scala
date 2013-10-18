package models

/**
 * User: casey
 * Created: 8:52 PM 10/17/13
 */
object Line {
  val TOP_ROW            = Line(Seq(Loc.TOP_LEFT,    Loc.TOP_MIDDLE,    Loc.TOP_RIGHT))
  val MIDDLE_ROW         = Line(Seq(Loc.MIDDLE_LEFT, Loc.MIDDLE,        Loc.MIDDLE_RIGHT))
  val BOTTOM_ROW         = Line(Seq(Loc.BOTTOM_LEFT, Loc.BOTTOM_MIDDLE, Loc.BOTTOM_RIGHT))

  val LEFT_COLUMN        = Line(Seq(Loc.TOP_LEFT,    Loc.MIDDLE_LEFT,   Loc.BOTTOM_LEFT))
  val MIDDLE_COLUMN      = Line(Seq(Loc.TOP_MIDDLE,  Loc.MIDDLE,        Loc.BOTTOM_MIDDLE))
  val RIGHT_COLUMN       = Line(Seq(Loc.TOP_RIGHT,   Loc.MIDDLE_RIGHT,  Loc.BOTTOM_RIGHT))

  val SLASH_DIAGONAL     = Line(Seq(Loc.BOTTOM_LEFT, Loc.MIDDLE,        Loc.TOP_RIGHT))
  val BACKSLASH_DIAGONAL = Line(Seq(Loc.TOP_LEFT,    Loc.MIDDLE,        Loc.BOTTOM_RIGHT))

  val all = Set(TOP_ROW, MIDDLE_ROW, BOTTOM_ROW,
                LEFT_COLUMN, MIDDLE_COLUMN, RIGHT_COLUMN,
                SLASH_DIAGONAL, BACKSLASH_DIAGONAL)

  def has(loc: Loc) = { all.filter(_.locs.contains(loc)) }
}

case class Line(locs: Seq[Loc])
