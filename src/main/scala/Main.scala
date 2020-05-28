package main

import geometry._

object Main {
  def main(args:Array[String])={

    // val n = 32
    // for(i <- 0 to n){

    //     val gen = new Generator("haha" + i + ".ppm");
    //     var m = Renderer.render(new Vector3d(7 * Math.cos(i * 2 *  3.141592/n),2.5,7 * Math.sin(i * 2 * 3.141592/n)), new Vector3d(0,0.5,0), 0.001, 30 )
    //     gen.loadScene(m)
    //     gen.save()
    //     println(i)
    // }

    val gen = new Generator("haha.ppm");
    var m = Renderer.render(new Vector3d(7,2.5,7), new Vector3d(0,0.5,0), 0.001, 30 )
    gen.loadScene(m)
    gen.save()
  }
}