package com.ldz.physics.body

import com.badlogic.gdx.math.{Rectangle, Shape2D, Vector2}

object BodyCenterOfMassInitialisation {

  implicit def comRectangleCreator(shape: Rectangle): Vector2 = {
    val com = new Vector2(0, 0)
    shape.getCenter(com)
    com
  }

  def createCenterOfMass[B <: Shape2D](shape: B)(implicit comCreator: (B => Vector2)): Vector2 = {
    comCreator.apply(shape)
  }

}
