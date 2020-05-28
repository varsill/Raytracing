package geometry
import java.beans.BeanProperty

import main.Settings

import math.sqrt

class Ray(@BeanProperty val origin: Point, @BeanProperty val dir:Vector3d) {
  def at(t:Double):Vector3d = { origin+dir*t}

  def rayColor(world: HittableList, depth:Int=0): Color= {
    if (depth >=Settings.MAX_DEPTH) return new Color(0,0,0)


    world.hit(this, Settings.EPSILON, Settings.INFINITY) match {
      case Some(rec)=>
      {

        rec.material.scatter(this, rec) match
        {
           case (Some(attenuation), Some(scattered))=> scattered.rayColor(world, depth+1) multiplyElementwise attenuation;
           case(None, None)=>new Color(0,0,0);
        }
       // val target: Point = rec.p + rec.normal + Vector3d.randomUnitVector()
       // new Ray(rec.p, target - rec.p).rayColor(world, depth+1)*0.5

      }
      case None =>
      {
        val t = 0.5 * (dir.unit.y + 1.0);
        new Color(1.0, 1.0, 1.0)* (1.0 - t) + (new Color(0.5, 0.7, 1.0))*t}
      }

  }
}


