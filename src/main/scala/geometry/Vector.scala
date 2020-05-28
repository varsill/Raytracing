 
package geometry
import math._
import scala.language.postfixOps
import scala.reflect.ClassTag
class Vector[T:Numeric](val coeffs: Array[T])(implicit c: ClassTag[T]) {


  def length_squared: T= {
        this*this
  }

  def +(that: Vector[T])={
    new Vector[T](this.coeffs.zip(that.coeffs).map[T]{ case (x:T, y:T)=> implicitly[Numeric[T]].plus(x,y)})
  }
  def -(that: Vector[T])={new Vector[T](this.coeffs zip that.coeffs map[T](x=>implicitly[Numeric[T]].minus(x._1,x._2)))}
  def unary_- = {new Vector[T](this.coeffs map((x)=>implicitly[Numeric[T]].negate(x)))}
  def *(that: Vector[T]): T ={this.coeffs zip that.coeffs map(x=>implicitly[Numeric[T]].times(x._1,x._2)) sum }
  def *(scalar: T): Vector[T] ={new Vector[T](this.coeffs.map[T](x=>implicitly[Numeric[T]].times(x, scalar))) }


  override def toString():String = {"("+coeffs.mkString(" ")+")"}



}

