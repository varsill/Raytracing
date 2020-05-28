package materials

import geometry.{Color, HitRecord, Ray, Vector3d}


abstract class Material
{
    def scatter(rayIn: Ray, hitRecord: HitRecord):(Option[Color], Option[Ray])
}


object Material
{
    def schlick(cosine: Double, refIdx: Double): Double =
    {
        val r0 = (1 - refIdx) / (1 + refIdx)
        val r02 = r0 * r0
        r02 + (1 - r02) * Math.pow(1.0 - cosine, 5f)
    }

    def refract(v: Vector3d, n: Vector3d, niOverNt: Double): Option[Vector3d] =
    {
        val uv = v.unit
        val dt = uv * n
        val discriminant = 1.0 - niOverNt * niOverNt * (1.0 - dt * dt)
        if(discriminant > 0f) Some((uv - n * dt) * niOverNt - n * Math.sqrt(discriminant).toFloat)
        else None
    }

}
