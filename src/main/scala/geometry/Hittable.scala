package geometry

trait Hittable {
  def hit(ray: Ray, tMin: Double, tMax: Double ): Option[HitRecord];
}