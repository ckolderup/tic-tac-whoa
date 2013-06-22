package models

import java.util.UUID

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 9:57 AM
 */

class Game(playerLabels: (String, String)) {
    val uuid: UUID = UUID.randomUUID
    val board: World = new World
    val players = (Player(playerLabels._1, "X"), Player(playerLabels._2, "O"))

    def currentPlayer = {
      board.lastTurn.player match {
        case players._1 => players._2
        case players._2 => players._1
      }
    }

    def requiredTile: Option[Loc] = {
      board.occupant(board.lastTurn.loc) match {
        case None => Some(board.lastTurn.loc)
        case Some(_: Player) => None
      }
    }

   def play(turn: Turn, tile: Loc) {
     board.place(turn, tile)
   }

  def winner: Option[Player] = board.getWinner
}
