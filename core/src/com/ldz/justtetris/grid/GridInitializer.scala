package com.ldz.justtetris.grid

import com.ldz.justtetris.constants.TetrisConstants
import com.ldz.justtetris.domain.{Coord, TetrisBlock, TetrisGrid}

object GridInitializer {

  def initializeGrid(): TetrisGrid = {

    val initialitedBlocks =
      for (
        x <- 1 to TetrisConstants.NB_WIDTH;
        y <- 1 to TetrisConstants.NB_HEIGHT
      ) yield (Coord(x, y), TetrisBlock.empty())

    TetrisGrid(initialitedBlocks.toMap)
  }

}
