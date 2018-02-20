package com.ldz.physics.gravity

import com.badlogic.gdx.math.Vector2
import com.ldz.physics.body.BodyWorldManager
import com.ldz.physics.domain.DynamicBody

object GravityApplyer {

  var isApplied = false

  def applyGravity(gravity: Vector2): Unit = {

    val bodys = BodyWorldManager.bodyContainer
    BodyWorldManager.bodyContainer = bodys map (body => body.setConstantAcceleration(gravity))

    if (!isApplied) {
      isApplied = true
    }

  }

}
