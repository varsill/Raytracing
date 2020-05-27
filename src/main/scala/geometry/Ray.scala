package main.geometry
import java.beans.BeanProperty
import math.sqrt

class Ray(@BeanProperty val origin: Point, @BeanProperty val dir:Vector3d) {
  def at(t:Double):Vector3d = { origin+dir*t}
  /*
  def hit_sphere(center: Point , radius:Double):Double={
    val oc: Vector3d = this.origin - center;
    val a = this.dir*this.dir
    val b =  oc*this.dir*2;
    val c = oc*oc - radius*radius;
    val discriminant = b*b - 4*a*c;
    if (discriminant < 0) {
      return -1.0;
    } else {
      return (-b - sqrt(discriminant) ) / (2.0*a);
    }
  }
   */

  def hit_sphere(center:Point, radius:Double):Double={
    val oc = this.origin - center
    val a = this.dir.length_squared
    val half_b = oc*this.dir
    val c = oc.length_squared - radius * radius
    val discriminant = half_b * half_b - a * c

    if (discriminant < 0) return -1.0
    else return (-half_b - sqrt(discriminant)) / a
  }



  def rayColor(world: HittableList): Color= {
    var INFINITY = 9999999.0

    world.hit(this, 0, INFINITY) match {
      case Some(rec)=>
      {
        //return new Color(1,0,0)
        return new Color(rec.normal + new Color(1, 1, 1)) * 0.5
      }
      case None =>
      { val t = 0.5 * (dir.unit.y + 1.0);
        new Color(1.0, 1.0, 1.0)* (1.0 - t) + (new Color(0.5, 0.7, 1.0))*t}
    }

/*
    if(hit_sphere(new Point(0, 0, -1), 0.5)>0)
      {
        return new Color(1,0,0)
      }
*/

  }
}



