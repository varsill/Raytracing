package geometry
import java.beans.BeanProperty

import main.Settings

import math.sqrt

class Ray(@BeanProperty val origin: Point, @BeanProperty val dir:Vector3d) {
  def at(t:Double):Vector3d = { origin+dir*t}


  def hit_sphere(center:Point, radius:Double):Double={
    val oc = this.origin - center
    val a = this.dir.length_squared
    val half_b = oc*this.dir
    val c = oc.length_squared - radius * radius
    val discriminant = half_b * half_b - a * c

    if (discriminant < 0) return -1.0
    else return (-half_b - sqrt(discriminant)) / a
  }



  def rayColor(world: HittableList, depth:Int=0): Color= {
    if (depth >=Settings.MAX_DEPTH) return new Color(0,0,0)


    world.hit(this, Settings.EPSILON, Settings.INFINITY) match {
      case Some(rec)=>
      {
        val target: Point = rec.p + rec.normal + Vector3d.randomUnitVector()
        new Ray(rec.p, target - rec.p).rayColor(world, depth+1)*0.5

      }
      case None =>
      {
        val t = 0.5 * (dir.unit.y + 1.0);
        new Color(1.0, 1.0, 1.0)* (1.0 - t) + (new Color(0.5, 0.7, 1.0))*t}
      }

  }
}




