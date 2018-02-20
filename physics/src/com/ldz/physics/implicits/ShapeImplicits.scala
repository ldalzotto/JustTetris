package com.ldz.physics.implicits

import com.badlogic.gdx.math.{Rectangle, Shape2D, Vector2}

object ShapeImplicits {

  implicit def rectangelMover(rectangle: Rectangle, positionToMove: Vector2): Rectangle = {
    new Rectangle(positionToMove.x, positionToMove.y, rectangle.width, rectangle.height)
  }


  implicit class ShapeImplicit(shape: Shape2D) {

    def move[T <: Shape2D](positionToMove: Vector2)(implicit mover: ((T, Vector2) => T)): T = {
      mover.apply(shape.asInstanceOf[T], positionToMove)
    }
  }

}
