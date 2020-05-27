package main.geometry

import math.sqrt
class Sphere(val center: Point, val radius:Double) extends Hittable {


  override def hit(ray: Ray, tMin: Double, tMax: Double): Option[HitRecord] =
  {
    //println(ray.dir)
    val oc = ray.origin - this.center
    val a = ray.dir.length_squared
    val half_b = oc*ray.dir
    val c = oc.length_squared - radius * radius
    val discriminant = half_b * half_b - a * c
    if (discriminant > 0)
    {
      val root = sqrt(discriminant)
      var temp = (-half_b - root) / a
      if (temp < tMax && temp > tMin)
      {
        val p = ray.at(temp)
        val outwardNormal = (p-center)/radius
        val hitRecord = new HitRecord(p, (p-center)/radius, temp)
        hitRecord.setFaceNormal(ray, outwardNormal)
        return Some(hitRecord)
      }
      temp = (-half_b + root) / a
      if (temp < tMax && temp > tMin)
      {
        val p = ray.at(temp)
        val outwardNormal = (p-center)/radius
        val hitRecord = new HitRecord(p, (p-center)/radius, temp)
        hitRecord.setFaceNormal(ray, outwardNormal)
        return Some(hitRecord)
      }
      None
    }
    else
    {
        None
    }
  }


}