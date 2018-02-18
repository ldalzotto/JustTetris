package com.ldz.justtetris.grid

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ldz.justtetris.domain.{Coord, TetrisBlock, TetrisGrid}

object GridManager {

  var tetrisGrid: TetrisGrid = GridInitializer.initializeGrid()

  def updateElements(): Unit = {
    tetrisGrid.tetrisBlocks filter filterNullBlocks foreach updateBlock
  }

  def draw(batch: SpriteBatch): Unit = {
    tetrisGrid.tetrisBlocks filter filterNullBlocks foreach drawBlock(batch)
  }

  def addBlock(block: (Coord, TetrisBlock)): Unit = {
    tetrisGrid.tetrisBlocks = tetrisGrid.tetrisBlocks + block
  }

  def removeBlock(block: (Coord, TetrisBlock)): Unit = {
    val (coord, currentBlock) = block
    val tetrisBlockToErase = getBlockAtCoord(coord)
    if (tetrisBlockToErase != null && !tetrisBlockToErase.isEmpty()) {
      tetrisBlockToErase.dispose()
    }

    addBlock((coord, TetrisBlock.empty()))
  }

  def getBlockAtCoord(coord: Coord): TetrisBlock = {
    try {
      tetrisGrid.tetrisBlocks(coord)
    } catch {
      case e: Throwable => null
    }
  }

  def getGrid(): Map[Coord, TetrisBlock] = tetrisGrid.tetrisBlocks

  type Line = Tuple2[Int, Seq[(Coord, TetrisBlock)]]

  def getLines(): Map[Int, Seq[(Coord, TetrisBlock)]] = {
    tetrisGrid.tetrisBlocks.toList groupBy (_._1.y.toInt)
  }

  def getColumns(): Map[Int, Seq[(Coord, TetrisBlock)]] = {
    tetrisGrid.tetrisBlocks.toList groupBy (_._1.x.toInt)
  }

  private def drawBlock(batch: SpriteBatch): PartialFunction[(Coord, TetrisBlock), Unit] = {
    case (_, tetrisBlock) => tetrisBlock.draw(batch)
  }

  private def updateBlock: PartialFunction[(Coord, TetrisBlock), Unit] = {
    case (_, tetrisBlock) => tetrisBlock.update()
  }

  private def filterNullBlocks: PartialFunction[(Coord, TetrisBlock), Boolean] = {
    case (_, tetrisBlock) => tetrisBlock != null
  }

}
