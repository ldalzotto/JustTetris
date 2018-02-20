package com.ldz.physics.domain

import com.badlogic.gdx.math.Vector2

case class GeometryStatus(centerOfMass: Vector2 = new Vector2(0, 0),
                          velocity: Vector2 = new Vector2(0, 0),
                          acceleration: Vector2 = new Vector2(0, 0)) {

  def +(that: GeometryStatus): GeometryStatus = {
    GeometryStatus(this.centerOfMass.add(that.centerOfMass), this.velocity.add(that.velocity), this.acceleration.add
    (that.acceleration))
  }

}
