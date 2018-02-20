package com.ldz.physics.domain

import com.badlogic.gdx.math.Vector2
import com.ldz.physics.body.BodyUpdater
import com.ldz.physics.contact.StaticContactor
import com.ldz.physics.gravity.GravityApplyer

/**
  * @param updateStep le timestep pour update le monde phyisique (en s)
  */
class World(updateStep: Float = 0.1f, gravity: Vector2 = new Vector2(0, 0)) {

  var timeAccumulator = 0F

  def update(delta: Float): Unit = {

    timeAccumulator = timeAccumulator + delta
    if (timeAccumulator >= updateStep) {
      updateChainCall(timeAccumulator)
      timeAccumulator = 0F
    }

  }

  private def updateChainCall(delta: Float): Unit = {

    GravityApplyer.applyGravity(gravity)
    StaticContactor.computeStaticContacts()
    BodyUpdater.update(delta)

  }

}
