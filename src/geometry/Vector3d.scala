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

object Vector3d
{
    val randomGenerator = scala.util.Random

    def randomDouble(min:Double, max:Double): Double = {
        randomGenerator.nextFloat()*(max-min)+min
    }

    def randomDouble(): Double = {
        randomDouble(0,1)
    }
    def random(min:Double, max: Double):Vector3d = new Vector3d(randomDouble(min, max), randomDouble(min, max), randomDouble(min, max))
    def random():Vector3d = random(0, 1)
    def randomInUnitSphere() =
    {
        var vec:Vector3d=random(-1, 1)
            while(vec.length_squared>=1) vec = random(-1, 1)
            vec
    }
    def randomUnitVector() =
        {
            val a = randomDouble(0, 2*Pi);
            val z = randomDouble(-1, 1);
            val r = sqrt(1 - z*z);
            new Vector3d(r*cos(a), r*sin(a), z);
        }

}


