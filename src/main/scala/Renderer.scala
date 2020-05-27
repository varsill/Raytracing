package main

import main.geometry.{Color, Point, Ray, Vector3d}

object Renderer {
  val viewportHeight = 2.0;
  val viewportWidth = viewportHeight*Settings.ASPECT_RATIO;
  val focalLength = 1.0;

  val origin:Point = new Point(0, 0, 0);
  val horizontal:Vector3d = new Vector3d(viewportWidth, 0, 0);
  val vertical:Vector3d = new Vector3d(0, viewportHeight, 0);
  val lower_left_corner:Vector3d = origin - horizontal/2 - vertical/2 - new Vector3d(0, 0, focalLength);

  def render() = {
   val matrix= Array.ofDim[Color](Settings.WIDTH, Settings.HEIGHT)

    for (i <-0 to Settings.WIDTH-1   ) {

      for (j <- 0 to Settings.HEIGHT-1) {
        val u = i.toDouble /(Settings.WIDTH-1);
        val v = j.toDouble/ (Settings.HEIGHT-1);
        val ray =new Ray(origin, (lower_left_corner + horizontal*u + vertical*v - origin));
        val pixelColor =  ray.rayColor()
        matrix(i)(j)=pixelColor
      }

    }
    matrix
  }

}
