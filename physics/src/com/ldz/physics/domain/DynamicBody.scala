package com.ldz.physics.domain

import com.badlogic.gdx.math.{Shape2D, Vector2}

/**
  * @param shape
  * @param geometryStatus
  * @param previousStatus
  * @param ponctualImpulses
  * @param friction friction, 0 to 1
  */
case class DynamicBody(shape: Shape2D, geometryStatus: GeometryStatus, previousStatus: GeometryStatus = null,
                       var ponctualImpulses: GeometryStatus = GeometryStatus(),
                       constantImpulses: GeometryStatus = GeometryStatus(), friction: Float = 0) {

  def addVelocity(velocity: Vector2): DynamicBody = {
    val geometryStatusWithVel = GeometryStatus(geometryStatus.centerOfMass, geometryStatus.velocity.add(velocity),
      geometryStatus.acceleration)

    this.copy(geometryStatus = geometryStatusWithVel)
  }

  def addAcceleration(acceleration: Vector2): DynamicBody = {
    val geometryStatusWithVel = GeometryStatus(geometryStatus.centerOfMass, geometryStatus.velocity,
      geometryStatus.acceleration.add(acceleration))

    this.copy(geometryStatus = geometryStatusWithVel)
  }

  def applyAcceleration(acceleration: Vector2): DynamicBody = {
    this.copy(ponctualImpulses = ponctualImpulses.copy(acceleration = acceleration))
  }

  def applyVelocity(velocity: Vector2): DynamicBody = {
    this.copy(ponctualImpulses = ponctualImpulses.copy(velocity = velocity))
  }

  def setConstantAcceleration(acceleration: Vector2): DynamicBody = {
    this.copy(constantImpulses = constantImpulses.copy(acceleration = acceleration))
  }

}