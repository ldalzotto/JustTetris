package com.ldz.physics.implicits

import com.badlogic.gdx.math.{Intersector, Rectangle, Shape2D, Vector2}

object ShapeIntersectorImplicits {

  implicit def rectToRectIntersect(rect1: Rectangle, rect2: Rectangle): Vector2 = {
    val overlappedRectangle = new Rectangle()
    if (Intersector.intersectRectangles(rect1, rect2, overlappedRectangle)) {
      val overlapCenter = new Vector2()
      overlappedRectangle.getCenter(overlapCenter)
      overlapCenter
    } else {
      null
    }

  }

  object ShapeIntersector {
    def overlap[S1 <: Shape2D, S2 <: Shape2D](shape1: S1, shape2: S2)(implicit overlapPointResolver: ((S1, S2) =>
      Vector2)): Vector2
    = {
      overlapPointResolver.apply(shape1, shape2)
    }

  }


}
