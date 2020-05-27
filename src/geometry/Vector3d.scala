package geometry
import math._
import scala.beans.BeanProperty



class Vector3d(@BeanProperty val x: Double, @BeanProperty  val y:Double, @BeanProperty val z:Double) extends Vector[Double](Array(x, y, z)) {
    def length = sqrt(length_squared)
    def /(scalar: Double) = {new Vector3d(x/scalar, y/scalar, z/scalar)}
    def unit = {this/length}
    override def *(scalar: Double) = {new Vector3d(x*scalar, y*scalar, z*scalar) }
    def +(that: Vector3d) = {new Vector3d(this.x+that.x, this.y+that.y, this.z+that.z) }
    def -(that: Vector3d) = {new Vector3d(this.x-that.x, this.y-that.y, this.z-that.z) }
    override def unary_- = {new Vector3d(-this.x,-this.y,-this.z)}
    def ==(that: Vector3d) = {this.x == that.x && this.y == that.y && this.z == that.z}

}


