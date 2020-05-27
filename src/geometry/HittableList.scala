package geometry

import scala.collection.LinearSeq
import scala.collection.mutable.ListBuffer

class HittableList{
    val  objects = new ListBuffer[Hittable]()
    def  add(hittable: Hittable)={
         objects+=hittable

    }

    def hit(r: Ray, tMin:Double, tMax:Double):Option[HitRecord]=
    {

      var wasHitted = false;
      var closestSoFar = tMax;
      var rec: HitRecord = null
      //return objects(0).hit(r, 0, 99.0)

      objects.foreach((f:Hittable)=>{
        f.hit(r, tMin, closestSoFar) match {
          case None=>
          case Some(tempRec) => {
            wasHitted = true;
            closestSoFar = tempRec.t;
            rec = tempRec;
          }
      }
      }
      )

      if(wasHitted) Some(rec) else None

      }

}
