package materials

import geometry.{Color, HitRecord, Ray}


abstract class Material {
    def scatter(rayIn: Ray, hitRecord: HitRecord):(Option[Color], Option[Ray])

};
