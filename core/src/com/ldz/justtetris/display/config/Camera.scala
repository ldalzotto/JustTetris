package com.ldz.justtetris.display.config

import com.badlogic.gdx.{Gdx, graphics}
import com.badlogic.gdx.graphics.{Camera, OrthographicCamera}
import com.badlogic.gdx.math.Matrix4

trait ICamera {
  def initialisePosition(): ICamera

  def update(): ICamera

  def projectionMatrix(): Matrix4

  def resize(width: Int, height: Int): ICamera

  def rawCamera(): Camera

  def initializeViewport(worldWidth: Int, worldHeight: Int): (ICamera, IViewport)

}

object Camera {

  private var instance: Option[ICamera] = None

  def getInstance(viewportWidht: Float, viewportHeight: Float, ratio: Tuple2[Float, Float]): ICamera = {
    instance = Some(instance.getOrElse(new Camera(viewportWidht, viewportHeight * (ratio._1 / ratio._2))))
    instance.get
  }

  def getInstance(): ICamera = {
    instance.getOrElse(throw new RuntimeException("Caméra non instanciée."))
  }

  def generateRatio(): Tuple2[Float, Float] = {
    Tuple2(Gdx.graphics.getWidth, Gdx.graphics.getHeight)
  }

  private class Camera(viewportWidht: Float, viewportHeight: Float) extends ICamera {

    def camera: OrthographicCamera = {
      new OrthographicCamera(viewportWidht, viewportHeight)
    }

    override def initialisePosition(): ICamera = {
      camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0)
      this
    }

    override def update(): ICamera = {
      camera.update()
      this
    }

    override def resize(width: Int, height: Int): ICamera = {
      camera.viewportWidth = viewportWidht
      camera.viewportHeight = viewportHeight * (height / width)
      this
    }


    override def initializeViewport(worldWidth: Int, worldHeight: Int): (ICamera, IViewport) = {
      (this, Viewport.getInstance(worldWidth, worldHeight, this))
    }

    override def rawCamera(): graphics.Camera = camera

    override def projectionMatrix(): Matrix4 = camera.combined

  }

}
