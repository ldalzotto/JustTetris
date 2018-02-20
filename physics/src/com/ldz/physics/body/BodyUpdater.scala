package com.ldz.physics.body


import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.ldz.physics.domain.{DynamicBody, GeometryStatus}

object BodyUpdater {


  def update(delta: Float): Unit = {

    val bodys = BodyWorldManager.bodyContainer

    val mutatedBodys = bodys map bodyAsTuple map updateCurrentState(delta) map reduceState map clearPonctuals

    BodyWorldManager.bodyContainer = mutatedBodys
  }

  def bodyAsTuple: PartialFunction[DynamicBody, (DynamicBody, DynamicBody)] = {
    case body => (body, body)
  }

  import com.ldz.physics.implicits.ShapeImplicits._

  def updateCurrentState(delta: Float): PartialFunction[(DynamicBody, DynamicBody), (DynamicBody, DynamicBody)] = {
    case (b1, b2) =>
      val accUpdatdGeometryStatus = updateAcceleration(b1.geometryStatus, b1.ponctualImpulses, b1.previousStatus, delta)
      val velUpdatedGeometryStatus = updateVelocity(accUpdatdGeometryStatus, b1.ponctualImpulses,
        b1.constantImpulses, delta)
      val posUpdatedGeometryStatus = updatePosition(velUpdatedGeometryStatus, b1.ponctualImpulses,
        b1.constantImpulses, delta)

      val movedShape = b1.shape.move(posUpdatedGeometryStatus.centerOfMass)

      (b1.copy(shape = movedShape, geometryStatus = posUpdatedGeometryStatus), b2)
    //TODO friction si nécessaire
    case b => b
  }


  private def updateAcceleration(geometryStatus: GeometryStatus, ponctualStatus: GeometryStatus,
                                 previousStatus: GeometryStatus,
                                 delta: Float): GeometryStatus = {
    def retroactiveAcceleration(): Vector2 = {
      if (previousStatus != null) {
        val finalSpeed = geometryStatus.velocity.cpy().add(ponctualStatus.velocity)
        val initialSpeed = previousStatus.velocity.cpy()

        val deltaSpeed = finalSpeed.add(initialSpeed.scl(-1))

        new Vector2(
          deltaSpeed.x / delta,
          deltaSpeed.y / delta
        )
      } else {
        new Vector2(0, 0)
      }

    }

    geometryStatus.copy(acceleration = geometryStatus.acceleration) //.add(retroactiveAcceleration()))
  }

  private def updateVelocity(geometryStatus: GeometryStatus, ponctualStatus: GeometryStatus,
                             constantImpulses: GeometryStatus, delta: Float)
  : GeometryStatus = {

    //calc velocity
    def calcVelocity(): Vector2 = {
      //calc acceleration
      val acceleration = geometryStatus.acceleration
      if (acceleration != null) {
        new Vector2(acceleration.x * delta, acceleration.y * delta)
      } else {
        new Vector2(0, 0)
      }
    }

    def calcDeltaPosConstantImpules(): Vector2 = {
      new Vector2(
        (constantImpulses.acceleration.x + ponctualStatus.acceleration.x) * delta,
        (constantImpulses.acceleration.y + ponctualStatus.acceleration.y) * delta
      )
    }

    geometryStatus.copy(velocity = geometryStatus.velocity.add(calcVelocity()).add
    (calcDeltaPosConstantImpules()).add(ponctualStatus.velocity))

  }

  private def updatePosition(geometryStatus: GeometryStatus, ponctualStatus: GeometryStatus,
                             constantImpulses: GeometryStatus,
                             delta: Float)
  : GeometryStatus = {
    //def calc position
    def calcPosition(): Vector2 = {
      val velocity = geometryStatus.velocity
      if (velocity != null) {
        new Vector2(geometryStatus.centerOfMass.x + (velocity.x * delta), geometryStatus.centerOfMass.y + (velocity
          .y * delta))
      } else {
        geometryStatus.centerOfMass
      }
    }

    def calcDeltaPosConstantImpules(): Vector2 = {
      new Vector2(
        constantImpulses.velocity.x * delta,
        constantImpulses.velocity.y * delta
      )
    }

    geometryStatus.copy(centerOfMass = calcPosition().add(calcDeltaPosConstantImpules()))
  }

  def reduceState: PartialFunction[(DynamicBody, DynamicBody), DynamicBody] = {
    case (b1, b2) =>
      b1.copy(previousStatus = b2.geometryStatus)
  }

  def clearPonctuals: PartialFunction[DynamicBody, DynamicBody] = {
    case b => b.copy(ponctualImpulses = GeometryStatus())
  }


}
