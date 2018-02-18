package com.ldz.justtetris.block

import com.ldz.justtetris.domain.{Coord, TetrisBlock}
import com.ldz.justtetris.grid.GridManager

object PlayerOwnedBlockManager {

  var ownedBlock: (Coord, TetrisBlock) = _

  def setElementToPlayer(tetrisBlock: (Coord, TetrisBlock)): Unit = {
    ownedBlock = tetrisBlock
    GridManager.addBlock(tetrisBlock)
  }

  def updateCoord(coord: Coord): Unit = {
    ownedBlock = (coord, ownedBlock._2)
  }

  def release(): Unit = ownedBlock = null

  def getOwnedBlock(): (Coord, TetrisBlock) = ownedBlock

  def isDefined(): Boolean = ownedBlock != null

}
