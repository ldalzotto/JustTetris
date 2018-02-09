package com.ldz.justtetris.display.config

import com.badlogic.gdx.utils.viewport.FillViewport

trait IViewport {
  def update(width: Int, height: Int): IViewport
}

object Viewport {

  private var instance: Option[IViewport] = None

  def getInstance(worldWidth: Int, worldHeight: Int, camera: ICamera): IViewport = {
    instance = Some(instance.getOrElse(new Viewport(worldWidth, worldHeight, camera)))
    instance.get
  }

  def getInstance(): IViewport = {
    instance.getOrElse(throw new RuntimeException("Caméra non instanciée."))
  }

  class Viewport(worldWidth: Int, worldHeight: Int, camera: ICamera) extends IViewport {

    def viewport: FillViewport = new FillViewport(worldWidth, worldHeight, camera.rawCamera())

    override def update(width: Int, height: Int): IViewport = {
      viewport.update(width, height)
      this
    }
  }

}

