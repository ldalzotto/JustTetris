package com.ldz.justtetris.score

import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.ldz.justtetris.domain.Score

object ScoreManager {

  var score = Score(0)
  val font = new BitmapFont()

  def addToScore(scoreToadd: Int) = {
    score = Score(score.currentScore + scoreToadd)
  }

  def draw(batch: SpriteBatch) = {
    font.draw(batch, s"${score.currentScore}", 10, 10)
  }

}
