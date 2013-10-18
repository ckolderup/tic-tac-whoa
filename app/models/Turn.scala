package models

/**
 * Audit trail of a turn. A game has a List of turns
 *
 * @author ckolderup
 * @since 6/20/13 1:33 PM
 */
case class Turn(player: Player, worldLoc: Loc, tileLoc: Loc)

