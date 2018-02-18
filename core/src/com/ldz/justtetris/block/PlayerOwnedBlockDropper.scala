package com.ldz.justtetris.block

import com.ldz.justtetris.grid.{DownDirection, GridElementMover}

object PlayerOwnedBlockDropper {

  var currentTimeElapsed = 0F
  var timeAfterDrop = 1F

  def update(delta: Float) = {

    currentTimeElapsed = currentTimeElapsed + delta
    if (currentTimeElapsed >= timeAfterDrop) {
      currentTimeElapsed = 0

      if (PlayerOwnedBlockManager.isDefined()) {
        val (ownedCoord, _) = PlayerOwnedBlockManager.getOwnedBlock()
        GridElementMover.moveElement(ownedCoord, DownDirection(), true)
      }

    }

  }

  def doubleTimeAfterDrop(): Unit = timeAfterDrop = timeAfterDrop / 8

  def timeBackToNormal(): Unit = timeAfterDrop = 1F

}
