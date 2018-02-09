package com.ldz.justtetris.display.config

import com.badlogic.gdx.graphics.g2d
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Matrix4

trait ISpriteBatch {
  def setProjectionMatrix(matrix: Matrix4): ISpriteBatch

  def drawSprite(sprite: Sprite): ISpriteBatch

  def begin(): ISpriteBatch

  def end(): ISpriteBatch

  def dispose(): ISpriteBatch
}

object SpriteBatch {

  private var instance: Option[ISpriteBatch] = None

  def getInstance(): ISpriteBatch = {
    instance = Some(instance.getOrElse(new SpriteBatch(new g2d.SpriteBatch())))
    instance.get
  }

  private class SpriteBatch(spriteBatch: g2d.SpriteBatch) extends ISpriteBatch {
    override def setProjectionMatrix(matrix: Matrix4): ISpriteBatch = {
      spriteBatch.setProjectionMatrix(matrix)
      this
    }


    override def drawSprite(sprite: Sprite): ISpriteBatch = {
      sprite.draw(spriteBatch)
      this
    }

    override def begin(): ISpriteBatch = {
      spriteBatch.begin()
      this
    }

    override def end(): ISpriteBatch = {
      spriteBatch.end()
      this
    }

    override def dispose(): ISpriteBatch = {
      spriteBatch.dispose()
      this
    }
  }

}
