package models

import java.util.UUID
import collection.mutable

/**
 * Represents a single game between two players
 *
 * @author ckolderup
 * @since 6/20/13 9:57 AM
 */

class NotYourTurnException extends Exception

class Game(playerLabels: (String, String)) {
    val uuid: UUID = UUID.randomUUID
    val players = Set(Player(playerLabels._1, "X"), Player(playerLabels._2, "O"))
    val board: World = new World

    protected val turns: mutable.MutableList[(Turn, Board)] = new mutable.MutableList()

    def lastTurn: Option[Turn] = if (turns.isEmpty) None else Some(turns.last._1)

    def currentPlayer: Player =
      players.diff( Set(lastTurn.map(_.player).getOrElse(players.last))).head

    def requiredTile: Option[Loc] = {
      lastTurn.flatMap(turn =>
        board.occupant(turn.worldLoc) match {
          case None => Some(turn.worldLoc)
          case Some(_: Player) => None

        }
      )
    }

   def play(turn: Turn) {
     if (currentPlayer != turn.player) throw new NotYourTurnException

     val result = board.place(turn)
     turns += Pair(turn, result)
   }

  def winner: Option[Player] = board.getWinner
}
