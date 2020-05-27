package geometry

import scala.beans.BeanProperty

class Color(@BeanProperty val r: Double, @BeanProperty  val g:Double, @BeanProperty val b:Double) extends Vector[Double](Array(r, g, b))
{

 override def toString() = {(255.999*r).toInt.toString+" "+(255.999*g).toInt.toString+" "+(255.999*b).toInt.toString+"\n"}

 override def *(scalar: Double) = {new Color(r*scalar, g*scalar, b*scalar) }
 def +(that: Color ) = {new Color(this.r+that.r, this.g+that.g, this.b+that.b) }
 def -(that: Color) = {new Color(this.r-that.r, this.g-that.g, this.b-that.b) }
}
