package main

object Settings {

  val WIDTH = 1500;
  val TYPE = "P3"; //file format
  val NUMBER_OF_COLOURS = 255;
  val ASPECT_RATIO = 16.0/9.0
  val HEIGHT = (WIDTH/ASPECT_RATIO).toInt
  val MAX_DEPTH = 2
  val INFINITY = 9999999.0
  val GAMMA = 0.5
  val EPSILON = 0.00001
}
