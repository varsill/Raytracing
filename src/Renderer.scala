

import geometry._
object Renderer {
  val randomGenerator = scala.util.Random

    def randomDouble(min:Double, max:Double): Double = {
        randomGenerator.nextFloat()*(max-min)+min
    }

    def randomDouble(): Double = {
          randomDouble(0,1)
    }
  def render() = {


    val camera:Camera = new Camera
    val world: HittableList = new HittableList()
    world.add(new Sphere(new Point(0,0,-1), 0.5))
    world.add(new Sphere(new Point(0,-100.5,-1), 100))



    val matrix= Array.ofDim[Color](Settings.WIDTH, Settings.HEIGHT)
    var ray:Ray=null;
    for (i <-0 to Settings.WIDTH-1   )
    {

      for (j <- 0 to Settings.HEIGHT-1)
      {
        var pixelColor = new Color(0,0,0)
        for (s <-0 until Color.samplesPerPixel)
        {
          val u = (i+randomDouble)/ (Settings.WIDTH - 1);
          val v = (j+randomDouble) / (Settings.HEIGHT - 1);
          ray = camera.getRay(u, v)
          pixelColor += ray.rayColor(world)
        }
        matrix(i)(j)=pixelColor
      }

    }
    matrix
  }

}
