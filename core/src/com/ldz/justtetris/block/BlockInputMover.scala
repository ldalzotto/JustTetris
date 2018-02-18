package com.ldz.justtetris.block

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.ldz.justtetris.domain.{Coord, TetrisBlock}
import com.ldz.justtetris.grid.{GridElementMover, LeftDirection, RightDirection}

object BlockInputMover {

  def checkforMoves(): Unit = {

    if (PlayerOwnedBlockManager.isDefined()) {
      val (playerCoord, _) = PlayerOwnedBlockManager.getOwnedBlock()
      if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
        GridElementMover.moveElement(playerCoord, LeftDirection(), true)
      } else if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
        GridElementMover.moveElement(playerCoord, RightDirection(), true)
      }

      if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
        PlayerOwnedBlockDropper.doubleTimeAfterDrop()
      } else if (!Gdx.input.isKeyPressed(Keys.DOWN)) {
        PlayerOwnedBlockDropper.timeBackToNormal()
      }
    }

  }

}
