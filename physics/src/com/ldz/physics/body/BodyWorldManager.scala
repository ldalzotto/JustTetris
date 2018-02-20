package com.ldz.physics.body

import com.badlogic.gdx.math.{Rectangle, Shape2D, Vector2}
import com.ldz.physics.domain.{DynamicBody, GeometryStatus, StaticBody}
import BodyCenterOfMassInitialisation._

object BodyWorldManager {

  var bodyContainer: Seq[DynamicBody] = Nil
  var staticBodyContainer: Seq[StaticBody] = Nil

  def addBody(shape: Rectangle): Unit = {
    bodyContainer = bodyContainer :+ BodyCreator.createDynamicBody(shape)
  }

  def addStaticBody(shape: Rectangle): Unit = {
    staticBodyContainer = staticBodyContainer :+ BodyCreator.createStaticBody(shape)
  }

  def addBody(body: DynamicBody): Unit = {
    bodyContainer = bodyContainer :+ body
  }

}
