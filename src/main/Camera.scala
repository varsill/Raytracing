package main

import geometry.{Point, Ray, Vector3d}

class Camera(val viewportHeight: Double = 2.0,val focalLength:Double = 1.0, val origin:Point = new Point(0,0,0) ) {

  val viewportWidth = viewportHeight*Settings.ASPECT_RATIO;
  val horizontal:Vector3d = new Vector3d(viewportWidth, 0, 0);
  val vertical:Vector3d = new Vector3d(0, viewportHeight, 0);
  val lower_left_corner:Vector3d = origin - horizontal/2 - vertical/2 - new Vector3d(0, 0, focalLength);


  def getRay(u:Double, v:Double)  =
  {
    new Ray(origin, lower_left_corner + horizontal*u + vertical*v - origin);
  }
}
