package geometry

import materials.Material

class HitRecord(val p: Point, var normal: Vector3d, val t: Double, val material: Material, var frontFace: Boolean = false) {

  def setFaceNormal(r: Ray, outwardNormal: Vector3d) {
    frontFace = r.dir * outwardNormal < 0;
    normal = if (frontFace) outwardNormal else -outwardNormal;
  }

}
