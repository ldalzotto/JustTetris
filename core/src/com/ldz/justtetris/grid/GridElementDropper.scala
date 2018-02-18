package com.ldz.justtetris.grid

import com.ldz.justtetris.domain.{Coord, TetrisBlock}

object GridElementDropper {

  def dropAllElements(): Unit = {
    val columns = GridManager.getColumns()

    columns foreach (e1 => dropAllColumnBlocks(e1._2))
  }

  private def dropAllColumnBlocks(column: Seq[(Coord, TetrisBlock)]): Unit = {
    var currentMove = 99

    while (currentMove != 0) {
      currentMove = column map (e1 => e1._1) map (e1 => GridElementMover.moveElement(e1, DownDirection
      ())) filter (_ != null) size
    }
  }

}
