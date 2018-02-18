package com.ldz.justtetris.domain

import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import com.badlogic.gdx.math.{Rectangle, Vector2}

case class TetrisBlock(shape: Rectangle, sprite: Sprite) {

  def update(): Unit = {
    if (shape != null && sprite != null) {
      val shapePosition: Vector2 = new Vector2()
      shape.getPosition(shapePosition)
      sprite.setPosition(shapePosition.x, shapePosition.y)
    }
  }

  def draw(batch: SpriteBatch) = {
    if (sprite != null) {
      sprite.draw(batch)
    }
  }

  def moveShape(x: Float, y: Float) = {
    shape.setPosition(shape.x + x, shape.y + y)
  }

  def isEmpty(): Boolean = shape == null && sprite == null

  def dispose(): Unit = {
    sprite.getTexture.dispose()
  }
}

object TetrisBlock {
  def empty(): TetrisBlock = TetrisBlock(null, null)
}
