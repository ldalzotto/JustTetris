package com.ldz.justtetris.domain

case class Coord(x: Float, y: Float) {

  def add(coord: Coord): Coord = {
    Coord(x + coord.x, y + coord.y)
  }

}