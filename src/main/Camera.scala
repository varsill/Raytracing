package main

import geometry.{Point, Ray, Vector3d}
import math._
class Camera(val lookFrom:Point, val lookAt:Point, val viewUp: Vector3d, val fieldOfView: Double, val aperture: Double, val focusDistance:Double) {


  val theta = toRadians(fieldOfView);
  val h = tan(theta/2);
  val viewportHeight = 2.0 * h;
  val viewportWidth = viewportHeight*Settings.ASPECT_RATIO;
  val lens_radius = aperture / 2;

  val w = (lookFrom-lookAt).unit
  val u = (viewUp cross w).unit
  val v = w cross u

  val origin = lookFrom
  val horizontal:Vector3d =  u*viewportWidth*focusDistance
  val vertical:Vector3d = v*viewportHeight*focusDistance
  val lower_left_corner:Vector3d = origin - horizontal/2 - vertical/2 - w*focusDistance;


  def getRay(s:Double, t:Double)  =
  {
    val rd =  Vector3d.randomInUnitDisk*lens_radius
    val offset = u * rd.x + v * rd.y

    new Ray(origin + offset, lower_left_corner + horizontal*s + vertical*t - origin - offset)
    //new Ray(origin, lower_left_corner + horizontal*u + vertical*v - origin);
  }
}
