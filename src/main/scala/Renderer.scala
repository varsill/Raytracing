package main

import geometry._
import geometry.Point


import materials.{Lambertian, Metal, Dielectric}

import math._

object Renderer {
  val randomGenerator = scala.util.Random

    def randomDouble(min:Double, max:Double): Double = {
        randomGenerator.nextFloat()*(max-min)+min
    }

    def randomDouble(): Double = {
          randomDouble(0,1)
    }
  def render(lookFrom:Point, lookAt:Point, aperture:Double, fieldOfView: Double) = {

    val viewUp = new Vector3d(0,4,0);

    val focusDistance = (lookFrom-lookAt).length;


    val camera:Camera = new Camera(lookFrom, lookAt, viewUp, fieldOfView, aperture, focusDistance)


    val lambertGrass = new Lambertian(new Color(1.0, 1.0, 0.0))
    val lambertGreen= new Lambertian(new Color(0.0, 1.0, 0.0))
    val lambertBlue= new Lambertian(new Color(0.0, 0.0, 1.0))
    val lambertRed= new Lambertian(new Color(1.0, 0.0, 0.0))
    val lambertBlack= new Lambertian(new Color(0.0, 0.0, 0.0))
    val lambertGrey= new Lambertian(new Color(0.5,0.5,0.5))
    val metal = new Metal(new Color(0.5, 0.7, 1.0))
    val metalGrey = new Metal(new Color(0.9, 0.9, 0.9))
    val metalRed = new Metal(new Color(1.0, 0.0, 0.0))
    val metalYellow = new Metal(new Color(1.0, 1.0, 0.875))
    val metalBlack = new Metal(new Color(0.2, 0.2, 0.2))
    val glass = new Dielectric(1.3)
    val world: HittableList = new HittableList()


    world.add(new Sphere(new Point(0,1,0), 1, metal))
    world.add(new Sphere(new Point(0,-100,0), 100, lambertGrey))
    world.add(new Sphere(new Point(1.5,0.3,-1.5), 0.25, lambertGreen))
    world.add(new Sphere(new Point(-3,1,0), 1, lambertRed))
    world.add(new Sphere(new Point(-2,0.5,-2), 0.5, lambertBlue))
    world.add(new Sphere(new Point(1.4,0.3,0), 0.25, metalYellow))
    world.add(new Sphere(new Point(0,0.25,-2), 0.25, metalRed))
    world.add(new Sphere(new Point(2,1,2.2), 1, glass))
    world.add(new Sphere(new Point(3,1,0), 1, metalYellow))
    world.add(new Sphere(new Point(1.5,0.5,-3), 0.5, metalBlack))
    world.add(new Sphere(new Point(2,0.25,-2), 0.25, lambertRed))
    world.add(new Sphere(new Point(-1,0.25,-3.5), 0.25, metalGrey))
    world.add(new Sphere(new Point(3.5,0.25,2), 0.25, metalYellow))
    world.add(new Sphere(new Point(-2.5,0.25,2), 0.25, lambertBlack))
    world.add(new Sphere(new Point(-1,0.25,3), 0.25, metalGrey))
    world.add(new Sphere(new Point(-0.5,0.5,2), 0.5, glass))
    world.add(new Sphere(new Point(0.5,0.4,3.5), 0.4, lambertGrass))
    



    val matrix= Array.ofDim[Color](Settings.WIDTH, Settings.HEIGHT)
    var ray:Ray=null;
    for (i <-0 to Settings.WIDTH-1   )
    {
      //println("Processing: ", i, "/", Settings.WIDTH-1)
      if(i%60 == 0){ print("#")}

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