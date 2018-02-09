package com.ldz.justtetris.display.config

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

case class RendererContainer(camera: ICamera, spriteBatch: ISpriteBatch) {

  def updateCamera(): RendererContainer = {
    camera.update()
    this
  }

  def clearImage(): RendererContainer = {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    this
  }

  def setProjectionMatrix(): RendererContainer = {
    spriteBatch.setProjectionMatrix(camera.projectionMatrix())
    this
  }

  def drawSpriteBatch(draw: (ISpriteBatch => Unit)): RendererContainer = {
    spriteBatch.begin()
    draw.apply(spriteBatch)
    spriteBatch.end()
    this
  }


}
