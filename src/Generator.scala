

import scala.language.reflectiveCalls
import java.io._
import geometry._
class Generator(private val filename:String) {

  var matrix: Array[Array[Color]] = Array.ofDim[Color](Settings.WIDTH, Settings.HEIGHT)

  def loadScene(matrix: Array[Array[Color]])=
  {
    this.matrix=matrix
  }

  def save()
  {
    def writeHeader(writer: PrintWriter): Unit =
    {
      writer.write(Settings.TYPE.toString);
      writer.write("\n");
      writer.write(Settings.WIDTH.toString);
      writer.write(" ");
      writer.write(Settings.HEIGHT.toString);
      writer.write("\n")
      writer.write(Settings.NUMBER_OF_COLOURS.toString);
      writer.write("\n")
    }

    val writer = new PrintWriter(new File(filename));
    try {
      writeHeader(writer);
      for(y <- 0 until Settings.HEIGHT)
      {

        for(x <-0 until Settings.WIDTH)
        {
          writer.write(matrix(x)(y).toString);
        }

      }
    println("Saving to file finished.");
    }
    catch
    {
      case e: Exception => e.printStackTrace();
    }
    finally
    {
      writer.close()
    }
  }
}
