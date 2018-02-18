package com.ldz.justtetris.grid

import com.ldz.justtetris.block.PlayerOwnedBlockManager
import com.ldz.justtetris.constants.TetrisConstants
import com.ldz.justtetris.domain.{Coord, TetrisBlock}

trait Direction {
  val direction: Coord
}

case class LeftDirection(direction: Coord = Coord(-1, 0)) extends Direction

case class RightDirection(direction: Coord = Coord(1, 0)) extends Direction

case class DownDirection(direction: Coord = Coord(0, -1)) extends Direction

object GridElementMover {

  def moveElement(coord: Coord, direction: Direction, playerElementConcerned: Boolean = false): TetrisBlock = {
    val movedCoord = coord.add(direction.direction)

    //get gridelements
    val currentGridElement = GridManager.getBlockAtCoord(coord)
    val movedCoordGridElement = GridManager.getBlockAtCoord(movedCoord)

    if (currentGridElement != null && movedCoordGridElement != null &&
      !currentGridElement.isEmpty()
      && movedCoordGridElement.isEmpty()) {

      GridManager.addBlock(coord, TetrisBlock.empty())
      currentGridElement.moveShape(direction.direction.x * TetrisConstants.BLOCK_SIZE, direction.direction.y *
        TetrisConstants.BLOCK_SIZE)

      if (playerElementConcerned) {
        PlayerOwnedBlockManager.updateCoord(movedCoord)
      }

      GridManager.addBlock(movedCoord, currentGridElement)
      return currentGridElement

    }

    null

  }

}
