package com.ldz.physics.implicits

import scala.collection.mutable.ArrayBuffer

object SeqImplicits {

  implicit class CoGroup[A, B](thisSeq: Seq[A]) {

    def coGroup(thatSeq: Seq[B]): Seq[(A, B)] = {
      val returnArrayBuffer: ArrayBuffer[(A, B)] = ArrayBuffer()
      thisSeq foreach (thisSeqVal => thatSeq foreach (thatSeqVal => returnArrayBuffer += ((thisSeqVal, thatSeqVal))))
      returnArrayBuffer
    }

  }

}
