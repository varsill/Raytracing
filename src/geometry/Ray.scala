package geometry
import java.beans.BeanProperty



import math.sqrt

class Ray(@BeanProperty origin: Point, @BeanProperty dir:Vector3d) {
  def at(t:Double):Vector3d = { origin+dir*t}
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
  def rayColor(): Color= {
    var t: Double = hit_sphere(new Point(0,-0.1,-2), 0.5)
    if(t>0.0) {
      val N: Vector3d = at(t) - new Vector3d(0, 0, -1)
      return new Color(N.x+1, N.y+1, N.z+1)*0.5
    }
    t = 0.5 * (dir.unit.y + 1.0);
    new Color(0.5, 0.7, 1.0)* (1.0 - t) + (new Color(1.0, 1.0, 1.0))*t
  }
}




