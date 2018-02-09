package com.ldz.justtetris

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.ldz.justtetris.display.config.{Camera, RendererContainer, SpriteBatch, Viewport}

trait IJustTetrisGame extends ApplicationAdapter

class JustTetrisGame extends IJustTetrisGame {

  override def create(): Unit = {
    Camera.getInstance(300, 300, Camera.generateRatio())
      .initializeViewport(300, 300)._1
      .initialisePosition()
      .update()
  }

  override def render(): Unit = {

    RendererContainer(Camera.getInstance(), SpriteBatch.getInstance())
      .updateCamera()
      .clearImage()
      .drawSpriteBatch(spriteBatch => {
        spriteBatch.drawSprite(new Sprite(new Texture(Gdx.files.absolute("C:\\Users\\Loic\\Documents\\JustTetris\\core\\assets\\badlogic.jpg"))))
      })

  }

  override def resize(width: Int, height: Int): Unit = {
    Viewport.getInstance()
      .update(width, height)

  }

  override def dispose(): Unit = {
    SpriteBatch.getInstance()
      .dispose()
  }
}
