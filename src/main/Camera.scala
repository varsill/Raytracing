package main

import geometry.{Point, Ray, Vector3d}
import math._
class Camera(val lookFrom:Point, val lookAt:Point, val viewUp: Vector3d, val fieldOfView: Double ) {


  val theta = toRadians(fieldOfView);
  val h = tan(theta/2);
  val viewportHeight = 2.0 * h;
  val viewportWidth = viewportHeight*Settings.ASPECT_RATIO;


  val w = (lookFrom-lookAt).unit
  val u = (viewUp cross w).unit
  val v = w cross u

  val origin = lookFrom
  val horizontal:Vector3d = u*viewportWidth
  val vertical:Vector3d = v*viewportHeight
  val lower_left_corner:Vector3d = origin - horizontal/2 - vertical/2 - w;


  def getRay(u:Double, v:Double)  =
  {
    new Ray(origin, lower_left_corner + horizontal*u + vertical*v - origin);
  }
}
