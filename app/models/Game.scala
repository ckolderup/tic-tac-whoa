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

  //TODO: could probably do something sneak with Either.swap
    def currentPlayer: Player = {
      board.lastTurn.map(_.player) match {
        case Some(players._1) => players._2
        case Some(players._2) => players._1
        case None => players._1
      }
    }

    def requiredTile: Option[Loc] = {
      board.lastTurn.flatMap(turn =>
        board.occupant(turn.loc) match {
          case None => Some(turn.loc)
          case Some(_: Player) => None

        }
      )
    }

   def play(turn: Turn, tile: Loc) {
     //TODO: handle a player trying to play twice in a row
     board.place(turn, tile)
   }

  def winner: Option[Player] = board.getWinner
}
