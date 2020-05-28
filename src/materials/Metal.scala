package materials

import geometry.{Color, HitRecord, Ray, Vector3d}

class Metal (val albedo: Color) extends Material
{

  def scatter(rayIn: Ray, hitRecord: HitRecord):(Option[Color], Option[Ray])=
  {
     val reflected = Vector3d.reflect(rayIn.dir.unit, hitRecord.normal);
     val scattered = new Ray(hitRecord.p, reflected);

     if (scattered.dir*hitRecord.normal > 0) (Some(albedo), Some(scattered))
     else (None, None)
  }

}
