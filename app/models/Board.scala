package models

import scala.collection.mutable
import play.api.libs.json.{JsArray, Json}

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 9:59 AM
 */
class InvalidPlacementException extends Exception

object Board {
  type Lines = Seq[Loc]

  val TOP_ROW           : Lines = Seq(Loc.TOP_LEFT,    Loc.TOP_MIDDLE,    Loc.TOP_RIGHT)
  val MIDDLE_ROW        : Lines = Seq(Loc.MIDDLE_LEFT, Loc.MIDDLE,        Loc.MIDDLE_RIGHT)
  val BOTTOM_ROW        : Lines = Seq(Loc.BOTTOM_LEFT, Loc.BOTTOM_MIDDLE, Loc.BOTTOM_RIGHT)

  val LEFT_COLUMN       : Lines = Seq(Loc.TOP_LEFT,    Loc.MIDDLE_LEFT,   Loc.BOTTOM_LEFT)
  val MIDDLE_COLUMN     : Lines = Seq(Loc.TOP_MIDDLE,  Loc.MIDDLE,        Loc.BOTTOM_MIDDLE)
  val RIGHT_COLUMN      : Lines = Seq(Loc.TOP_RIGHT,   Loc.MIDDLE_RIGHT,  Loc.BOTTOM_RIGHT)

  val SLASH_DIAGONAL    : Lines = Seq(Loc.BOTTOM_LEFT, Loc.MIDDLE,        Loc.TOP_RIGHT)
  val BACKSLASH_DIAGONAL: Lines = Seq(Loc.TOP_LEFT,    Loc.MIDDLE,        Loc.BOTTOM_RIGHT)

  protected def getLines(loc: Loc): Seq[Lines] = loc match {
    case Loc.TOP_LEFT      => Seq(TOP_ROW, LEFT_COLUMN, BACKSLASH_DIAGONAL)
    case Loc.TOP_MIDDLE    => Seq(TOP_ROW, MIDDLE_COLUMN)
    case Loc.TOP_RIGHT     => Seq(TOP_ROW, RIGHT_COLUMN, SLASH_DIAGONAL)
    case Loc.MIDDLE_LEFT   => Seq(MIDDLE_ROW, LEFT_COLUMN)
    case Loc.MIDDLE        => Seq(MIDDLE_ROW, MIDDLE_COLUMN, SLASH_DIAGONAL, BACKSLASH_DIAGONAL)
    case Loc.MIDDLE_RIGHT  => Seq(MIDDLE_ROW, RIGHT_COLUMN)
    case Loc.BOTTOM_LEFT   => Seq(BOTTOM_ROW, LEFT_COLUMN, SLASH_DIAGONAL)
    case Loc.BOTTOM_MIDDLE => Seq(BOTTOM_ROW, MIDDLE_COLUMN)
    case Loc.BOTTOM_RIGHT  => Seq(BOTTOM_ROW, RIGHT_COLUMN, BACKSLASH_DIAGONAL)
  }
}

trait Board[T] {
  import Board._

  def hasWinner: Boolean = getWinner.isDefined
  def lastTurn: Option[Turn] = if (turns.isEmpty) None else Some(turns.last)

  def occupant(loc: Loc): Option[Player]
  def owns(player: Player, lines: Lines): Boolean = lines.forall(l => occupant(l) == Some(player))

  def getWinner: Option[Player] = {
    lastTurn.flatMap(turn =>
      getLines(turn.loc).exists(line => owns(turn.player, line)) match {
        case true => Some(turn.player)
        case false => None
      }
    )
  }

  protected val spots: Array[Array[T]]
  protected val turns: mutable.MutableList[Turn] = new mutable.MutableList()
}
