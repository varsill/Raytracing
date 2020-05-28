package materials

import geometry.{Color, HitRecord, Ray, Vector3d}

import scala.util.Random

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