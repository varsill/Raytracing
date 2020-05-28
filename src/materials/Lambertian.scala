package materials

import geometry.{Color, HitRecord, Ray, Vector3d}

class Lambertian(val albedo:Color) extends Material {

    def scatter(rIn:Ray, rec: HitRecord): (Option[Color], Option[Ray])=
    {
      val scatter_direction = rec.normal + Vector3d.randomUnitVector();
      val scattered = (new Ray(rec.p, scatter_direction*0.59));
      (Some(albedo),Some(scattered))
    }

}
