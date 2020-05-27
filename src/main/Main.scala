package main

import java.awt.Dialog

import javax.swing.ImageIcon

object Main {
def main(args:Array[String])={


  val gen = new Generator("haha.ppm");
  var m = Renderer.render()

  gen.loadScene(m)
  gen.save()

}
}
