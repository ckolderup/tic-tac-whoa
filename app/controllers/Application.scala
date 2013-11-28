package controllers

import play.api._
import libs.json.{JsString, JsObject, Json}
import play.api.mvc._
import models.{Turn, Loc, Game}
import java.util.UUID
import collection.mutable

object Application extends Controller {
  val games: mutable.Map[UUID, Game] = mutable.HashMap()

  class InvalidRequestException(msg: String) extends RuntimeException(msg)

  def index() = Action { Ok }

  def createGame(playerX: String, playerO: String) = Action {
    val game = new Game(playerX, playerO)
    games += (game.uuid -> game)

    Ok(
      Json.obj(
        "msg" -> "ok",
        "id" -> game.uuid.toString
      )
    )
  }

  //TODO: should we be passing player name? What else would we pass?
  def playTurn(game: String, playerName: String, worldX: Int, worldY: Int, boardX: Int, boardY: Int) = Action {
    try {
      games.get(UUID.fromString(game)) match {
        case Some(game: Game) =>
          assert(game.players.size == 2) //for now
          val player1 = game.players.head
          val player2 = game.players.tail.head
          val player = (player1.name, player2.name) match {
            case (`playerName`, _) => player1
            case (_, `playerName`) => player2
            case _ => throw new InvalidRequestException("no such player " + playerName)
          }

          //TODO: handle exceptions thrown by game
          game.play(Turn(player, Loc(boardX, boardY), Loc(worldX, worldY)))
          Ok(
            Json.obj(
              "msg" -> JsString("ok"),
              "winner" -> Json.toJson(game.winner.map(_.name))
            )
          )
        case None => throw new InvalidRequestException("no such game")
      }
    } catch {
      case e: InvalidRequestException => NotFound(Json.obj("msg" -> e.getMessage))
    }
  }
}
