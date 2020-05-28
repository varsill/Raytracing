package geometry

import materials.Material
import scala.math.sqrt

class Sphere(val center: Point, val radius: Double, val material: Material) extends Hittable {

  override def hit(ray: Ray, tMin: Double, tMax: Double): Option[HitRecord] = {

    val oc = ray.origin - this.center
    val a = ray.dir.length_squared
    val half_b = oc * ray.dir
    val c = oc.length_squared - radius * radius
    val discriminant = half_b * half_b - a * c

    if (discriminant > 0) {
      val root = sqrt(discriminant)
      var temp = (-half_b - root) / a
      if (temp < tMax && temp > tMin) {
        val p = ray.at(temp)
        val outwardNormal = (p - center) / radius
        val hitRecord = new HitRecord(p, (p - center) / radius, temp, material)
        hitRecord.setFaceNormal(ray, outwardNormal)
        return Some(hitRecord)
      }

      temp = (-half_b + root) / a
      if (temp < tMax && temp > tMin) {
        val p = ray.at(temp)
        val outwardNormal = (p - center) / radius
        val hitRecord = new HitRecord(p, (p - center) / radius, temp, material)
        hitRecord.setFaceNormal(ray, outwardNormal)
        return Some(hitRecord)
      }
      None
    }
    else None
  }

}
