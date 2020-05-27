package main


object Settings {

  val WIDTH = 1500;
  val TYPE = "P3"; //file format
  val NUMBER_OF_COLOURS = 255;
  val ASPECT_RATIO = 16.0/9.0
  val HEIGHT = (WIDTH/ASPECT_RATIO).toInt
}
