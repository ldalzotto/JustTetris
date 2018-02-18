package com.ldz.justtetris.block

import com.ldz.justtetris.grid.{DownDirection, GridManager}

object PlayerOwnedBlockStopper {

  def stopPlayerBlockIfNecessary(): Boolean = {

    if (PlayerOwnedBlockManager.isDefined()) {

      val (coordOwned, _) = PlayerOwnedBlockManager.getOwnedBlock()
      val downCoord = coordOwned.add(DownDirection().direction)
      val futureElement = GridManager.getBlockAtCoord(downCoord)
      if (futureElement == null || !futureElement.isEmpty()) {
        PlayerOwnedBlockManager.release()
        return true
      }

    }
    false

  }

}
