package geometry
import main.Settings
import math.pow
import scala.beans.BeanProperty

class Color(@BeanProperty var r: Double, @BeanProperty  var g:Double, @BeanProperty var b:Double) extends Vector[Double](Array(r, g, b))
{
 def this(vector: Vector[Double])=this(vector.coeffs(0), vector.coeffs(1), vector.coeffs(2))
 override def toString() = {
  val scale = 1.0 / Color.samplesPerPixel;
  val red = pow(r*scale, Settings.GAMMA)
  val green = pow(g*scale, Settings.GAMMA)
  val blue = pow(b*scale, Settings.GAMMA)
  (256 * Color.clamp(red, 0.0, 0.999)).toInt.toString+" "+(256 * Color.clamp(green, 0.0, 0.999)).toInt.toString+" "+(256 * Color.clamp(blue, 0.0, 0.999)).toInt.toString+"\n"

 }

 override def *(scalar: Double) = {new Color(r*scalar, g*scalar, b*scalar) }
 def +(that: Color ) = {new Color(this.r+that.r, this.g+that.g, this.b+that.b) }
 def -(that: Color) = {new Color(this.r-that.r, this.g-that.g, this.b-that.b) }
 def +=(that: Color ) = {this.r+=that.r; this.g+=that.g; this.b+=that.b; }

 def multiplyElementwise(that: Color) = new Color(this.r*that.r, this.g*that.g, this.b*that.b)
}


object Color
{
 val samplesPerPixel = 10
 def clamp(x:Double, min:Double, max:Double ):Double  = if (x < min)  min else if(x>max)  max else  x
}