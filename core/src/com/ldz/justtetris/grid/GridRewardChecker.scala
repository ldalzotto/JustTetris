package com.ldz.justtetris.grid

import com.ldz.justtetris.domain.{Coord, TetrisBlock}
import com.ldz.justtetris.score.ScoreManager

object GridRewardChecker {

  def applyReward(): Int = {
    val lines = GridManager.getLines()

    val computedLines = lines map isLineFull filter (e2 => e2._2) map (el => el._3)

    computedLines foreach addScore
    computedLines foreach deleteLine

    computedLines.size
  }

  def isLineFull: PartialFunction[(Int, Seq[(Coord, TetrisBlock)]), (Int, Boolean, Seq[(Coord, TetrisBlock)])] = {
    case (lineIndex, lineBlocks) =>
      val lineWithElementTransformed = lineBlocks map (line => !line._2.isEmpty())
      if (lineWithElementTransformed.contains(false)) (lineIndex, false, lineBlocks)
      else (lineIndex, true, lineBlocks)
  }


  def deleteLine: PartialFunction[Seq[(Coord, TetrisBlock)], Unit] = {
    case blocks => blocks foreach (block => GridManager.removeBlock(block))
  }

  def addScore: PartialFunction[Seq[(Coord, TetrisBlock)], Unit] = {
    case blocks => blocks foreach (block => ScoreManager.addToScore(1))
  }

}
