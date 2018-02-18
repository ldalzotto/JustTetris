package com.ldz.justtetris.block

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.ldz.justtetris.constants.TetrisConstants
import com.ldz.justtetris.domain.{Coord, TetrisBlock}

object BlockSpawner {

  def checkForSpawn(): Unit = {
    if (Gdx.input.isKeyJustPressed(Keys.SPACE) && !PlayerOwnedBlockManager.isDefined()) {
      spawn()
    }
  }

  private def spawn(): Unit = {
    val tetrisBlock: TetrisBlock = BlockBuilder.builBlock(3 * TetrisConstants.BLOCK_SIZE, 8 * TetrisConstants
      .BLOCK_SIZE)
    PlayerOwnedBlockManager.setElementToPlayer((Coord(3, 8), tetrisBlock))
  }

}
