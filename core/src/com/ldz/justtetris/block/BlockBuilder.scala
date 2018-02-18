package com.ldz.justtetris.block

import com.badlogic.gdx.graphics.{Color, Pixmap, Texture}
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.ldz.justtetris.constants.TetrisConstants
import com.ldz.justtetris.domain.TetrisBlock

object BlockBuilder {

  def builBlock(x: Int, y: Int): TetrisBlock = {

    val blockShape: Rectangle = new Rectangle(x, y, TetrisConstants.BLOCK_SIZE, TetrisConstants.BLOCK_SIZE)
    val pixmap: Pixmap = new Pixmap(TetrisConstants.BLOCK_SIZE, TetrisConstants.BLOCK_SIZE, Pixmap.Format.RGBA8888)
    pixmap.setColor(Color.BLUE)

    pixmap.fillRectangle(1, 1, TetrisConstants.BLOCK_SIZE, TetrisConstants.BLOCK_SIZE)

    val sprite: Sprite = new Sprite(new Texture(pixmap))
    sprite.setPosition(x, y)

    pixmap.dispose()

    TetrisBlock(blockShape, sprite)
  }

}
