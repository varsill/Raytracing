package materials

import main._
import geometry._


import scala.util.Random


abstract class Material {
    def scatter(rayIn: Ray, hitRecord: HitRecord):(Option[Color], Option[Ray])
};

object Material {
  def schlick(cosine: Double, refIdx: Double): Double = {
    val r0 = (1 - refIdx) / (1 + refIdx)
    val r02 = r0 * r0
    r02 + (1 - r02) * Math.pow(1.0 - cosine, 5f)
  }

  def refract(v: Vector3d, n: Vector3d, niOverNt: Double): Option[Vector3d] = {
    val uv = v.unit
    val dt = uv * n
    val discriminant = 1.0 - niOverNt * niOverNt * (1.0 - dt * dt)
    if(discriminant > 0f){
      Some(
        (uv - n * dt) * niOverNt - n * Math.sqrt(discriminant).toFloat
      )
    } else {
      None
    }
  }
}

class Lambertian(val albedo:Color) extends Material {

    def scatter(rIn:Ray, rec: HitRecord): (Option[Color], Option[Ray])=
    {
      val scatter_direction = rec.normal + Vector3d.randomUnitVector();
      val scattered = (new Ray(rec.p, scatter_direction*0.59));
      (Some(albedo),Some(scattered))
    }

}

class Metal (val albedo: Color) extends Material {

 def scatter(rayIn: Ray, hitRecord: HitRecord):(Option[Color], Option[Ray])=
  {
  val reflected = Vector3d.reflect(rayIn.dir.unit, hitRecord.normal);
  val scattered = new Ray(hitRecord.p, reflected);

  if (scattered.dir*hitRecord.normal > 0) (Some(albedo), Some(scattered)) else (None, None)
}

}

class Dielectric (refIdx: Double) extends Material{
  def scatter(rayIn: Ray, hitRecord: HitRecord):(Option[Color], Option[Ray]) = {
    val reflected = Vector3d.reflect(rayIn.dir.unit, hitRecord.normal);
    val attenuation = new Color(1.0, 1.0, 1.0)

    var cosine: Double = 0
    var niOverNt: Double = 0
    var outwardNormal = new Vector3d(0,0,0)

    if(rayIn.dir * hitRecord.normal > 0){
      outwardNormal = -hitRecord.normal
      niOverNt = refIdx
      var cosine = rayIn.dir * refIdx * hitRecord.normal / rayIn.dir.length
    }
    else{
      outwardNormal = hitRecord.normal
      niOverNt = 1.0/refIdx
      cosine = -(rayIn.dir * refIdx * hitRecord.normal) / rayIn.dir.length
    }

    val reflectProb = Material.schlick(cosine, refIdx)

    Material.refract(rayIn.dir, outwardNormal, niOverNt) match{
      case Some(refracted) if reflectProb <= Random.nextDouble() =>
        (Some(attenuation), Some(new Ray(hitRecord.p, refracted)))
      case _ => 
         (Some(attenuation), Some(new Ray(hitRecord.p, reflected)))
    }

  }


}