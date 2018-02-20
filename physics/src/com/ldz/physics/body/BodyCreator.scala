package com.ldz.physics.body

import com.badlogic.gdx.math.{Rectangle, Shape2D}
import com.ldz.physics.domain.{DynamicBody, GeometryStatus, StaticBody}

object BodyCreator {

  def createDynamicBody(shape: Shape2D): DynamicBody = {

    val com = BodyCenterOfMassInitialisation.comRectangleCreator(shape.asInstanceOf[Rectangle])
    DynamicBody(shape, GeometryStatus(com))
  }

  def createStaticBody(shape: Shape2D): StaticBody = {

    val com = BodyCenterOfMassInitialisation.comRectangleCreator(shape.asInstanceOf[Rectangle])
    StaticBody(shape, GeometryStatus(com))
  }

}
