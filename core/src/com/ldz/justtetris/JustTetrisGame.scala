package com.ldz.justtetris

import com.badlogic.gdx.graphics.{OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import com.badlogic.gdx.math.{Intersector, Rectangle}
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.ldz.justtetris.block.{BlockInputMover, BlockSpawner, PlayerOwnedBlockDropper, PlayerOwnedBlockStopper}
import com.ldz.justtetris.grid.{GridElementDropper, GridManager, GridRewardChecker}
import com.ldz.justtetris.score.ScoreManager

class JustTetrisGame extends ApplicationAdapter {

  var camera: OrthographicCamera = _
  var viewport: FitViewport = _
  var batch: SpriteBatch = _

  override def resume(): Unit = super.resume()

  override def pause(): Unit = super.pause()

  override def create(): Unit = {
    camera = new OrthographicCamera(70, 70)
    viewport = new FitViewport(400, 400, camera)
    viewport.apply()
    batch = new SpriteBatch()
  }

  override def resize(width: Int, height: Int): Unit = {
    camera.update()
    viewport.update(width, height)
  }

  override def dispose(): Unit = super.dispose()

  override def render(): Unit = {
    import com.badlogic.gdx.Gdx
    import com.badlogic.gdx.graphics.GL20


    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    val deltaTime = Gdx.graphics.getDeltaTime

    camera.update()
    batch.setProjectionMatrix(camera.combined)
    batch.begin()

    BlockSpawner.checkForSpawn()
    BlockInputMover.checkforMoves()
    PlayerOwnedBlockDropper.update(deltaTime)

    val isPlayerStopped = PlayerOwnedBlockStopper.stopPlayerBlockIfNecessary()
    if (isPlayerStopped) {
      val rowsDeleted = GridRewardChecker.applyReward()
      if (rowsDeleted > 0) {
        GridElementDropper.dropAllElements()
      }
    }

    GridManager.updateElements()
    GridManager.draw(batch)
    ScoreManager.draw(batch)

    batch.end()
  }
}
