package com.ldz.physics.contact


import com.badlogic.gdx.math.{Intersector, Rectangle, Vector2}
import com.ldz.physics.body.BodyWorldManager
import com.ldz.physics.domain.{DynamicBody, GeometryStatus, StaticBody}
import com.ldz.physics.implicits.SeqImplicits._
import com.ldz.physics.implicits.ShapeIntersectorImplicits.rectToRectIntersect

object StaticContactor {

  def computeStaticContacts(): Unit = {

    val dynamicBodys = BodyWorldManager.bodyContainer
    val staticBodys = BodyWorldManager.staticBodyContainer

    val groupedBodys = staticBodys coGroup dynamicBodys

    val groupedContacts = groupedBodys map manageContact filter (d => d._2 != null) groupBy (d => d._1)
    groupedContacts.toSeq map sumContactsOnSameBody foreach updatePonctualContactOnBody

  }

  def manageContact: PartialFunction[(StaticBody, DynamicBody), (DynamicBody, GeometryStatus)] = {
    case (staticBody, dynamicBody) =>
      //val overlapPoint = ShapeIntersector.overlap(staticBody.shape, dynamicBody.shape)
      val overlapPoint = rectToRectIntersect(staticBody.shape.asInstanceOf[Rectangle], dynamicBody.shape
        .asInstanceOf[Rectangle])
      if (overlapPoint != null) {
        val constactResultGeom = GeometryStatus(new Vector2(0, 0), dynamicBody.geometryStatus.velocity.cpy().scl
        (-1.7f).add
        (dynamicBody.constantImpulses.velocity.cpy().scl(-1.7f)).add(dynamicBody.ponctualImpulses.velocity.cpy().scl
        (-1.7f)), dynamicBody.ponctualImpulses.acceleration.cpy().scl(-2).add(dynamicBody.constantImpulses
          .acceleration.cpy().scl(-2)))
        (dynamicBody, constactResultGeom)
      } else {
        (dynamicBody, null)
      }
  }

  def sumContactsOnSameBody: PartialFunction[(DynamicBody, Seq[(DynamicBody, GeometryStatus)]), (DynamicBody,
    GeometryStatus)] = {
    case (dynamicBody, allBodies) =>
      var summedGeometry: GeometryStatus = null
      allBodies foreach (currentBody => {
        if (summedGeometry == null) {
          summedGeometry = currentBody._2
        } else {
          summedGeometry = summedGeometry + currentBody._2
        }
      })
      (dynamicBody, summedGeometry)

  }


  def updatePonctualContactOnBody(pair: (DynamicBody, GeometryStatus)): Unit = {
    pair._1.ponctualImpulses = pair._2
  }

}
