

import geometry._
object Renderer {
  val viewportHeight = 2.0;
  val viewportWidth = viewportHeight*Settings.ASPECT_RATIO;
  val focalLength = 1.0;

  val origin:Point = new Point(0, 0, 0);
  val horizontal:Vector3d = new Vector3d(viewportWidth, 0, 0);
  val vertical:Vector3d = new Vector3d(0, viewportHeight, 0);
  val lower_left_corner:Vector3d = origin - horizontal/2 - vertical/2 - new Vector3d(0, 0, focalLength);


  def render() = {

    val world: HittableList = new HittableList()
    world.add(new Sphere(new Point(0,0,-1), 0.5))
    world.add(new Sphere(new Point(0,-100.5,-1), 100))



    val matrix= Array.ofDim[Color](Settings.WIDTH, Settings.HEIGHT)

    for (i <-0 to Settings.WIDTH-1   ) {

      for (j <- 0 to Settings.HEIGHT-1) {
        val u = i.toDouble /(Settings.WIDTH-1);
        val v = j.toDouble/ (Settings.HEIGHT-1);
        val ray =new Ray(origin, (lower_left_corner + horizontal*u + vertical*v - origin));
        val pixelColor =  ray.rayColor(world)
        matrix(i)(j)=pixelColor
      }

    }
    matrix
  }

}
