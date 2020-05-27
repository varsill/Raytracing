import geometry._

object Main {
def main(args:Array[String])={


  val gen = new Generator("haha.ppm");
  var m = Renderer.render()

  gen.loadScene(m)
  gen.save()
}
}