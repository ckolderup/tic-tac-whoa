package models

import java.util.UUID

/**
 * TODO: Document me
 *
 * @author ckolderup
 * @since 6/20/13 9:57 AM
 */
class Game {
    val uuid: UUID = UUID.randomUUID
    val board: World = new World
}
