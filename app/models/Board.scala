package models

import scala.collection.mutable
import scala.reflect.ClassTag

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 9:59 AM
 */
class InvalidPlacementException extends Exception

object Board {
  type Lines = Seq[Loc]

  val TOP_ROW           : Lines = Seq(Loc(0, 0), Loc(0, 1), Loc(0, 2))
  val MIDDLE_ROW        : Lines = Seq(Loc(1, 0), Loc(1, 1), Loc(1, 2))
  val BOTTOM_ROW        : Lines = Seq(Loc(2, 0), Loc(2, 1), Loc(2, 2))

  val LEFT_COLUMN       : Lines = Seq(Loc(0, 0), Loc(1, 0), Loc(2, 0))
  val MIDDLE_COLUMN     : Lines = Seq(Loc(0, 1), Loc(1, 1), Loc(2, 1))
  val RIGHT_COLUMN      : Lines = Seq(Loc(0, 2), Loc(1, 2), Loc(2, 2))

  val SLASH_DIAGONAL    : Lines = Seq(Loc(0, 2), Loc(1, 1), Loc(2, 0))
  val BACKSLASH_DIAGONAL: Lines = Seq(Loc(0, 0), Loc(1, 1), Loc(2, 2))

  protected def getLines(loc: Loc): Seq[Lines] = {
    loc match {
      case Loc(0, 0) => Seq(TOP_ROW, LEFT_COLUMN, BACKSLASH_DIAGONAL)
      case Loc(0, 1) => Seq(TOP_ROW, MIDDLE_COLUMN)
      case Loc(0, 2) => Seq(TOP_ROW, RIGHT_COLUMN, SLASH_DIAGONAL)
      case Loc(1, 0) => Seq(MIDDLE_ROW, LEFT_COLUMN)
      case Loc(1, 1) => Seq(MIDDLE_ROW, MIDDLE_COLUMN, SLASH_DIAGONAL, BACKSLASH_DIAGONAL)
      case Loc(1, 2) => Seq(MIDDLE_ROW, RIGHT_COLUMN)
      case Loc(2, 0) => Seq(BOTTOM_ROW, LEFT_COLUMN, SLASH_DIAGONAL)
      case Loc(2, 1) => Seq(BOTTOM_ROW, MIDDLE_COLUMN)
      case Loc(2, 2) => Seq(BOTTOM_ROW, RIGHT_COLUMN, BACKSLASH_DIAGONAL)
    }
  }
}

trait Board[T] {
  import Board._

  def hasWinner: Boolean = getWinner.isDefined
  def lastTurn: Turn = turns.last

  def occupies(player: Player, loc: Loc): Boolean
  def owns(player: Player, lines: Lines): Boolean = lines.forall(l => occupies(player, l))

  def getWinner: Option[Player] =
    getLines(lastTurn.loc).exists(line => owns(lastTurn.player, line)) match {
      case true => Some(lastTurn.player)
      case false => None
    }

  protected def mkArray(implicit m: Manifest[T]): Array[Array[T]] = Array.ofDim[T](3, 3)
  protected val spots: Array[Array[T]]
  protected val turns: mutable.MutableList[Turn] = new mutable.MutableList()
}