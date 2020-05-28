package main

object Settings {

  var WIDTH = 300;
  var TYPE = "P3"; //file format
  var NUMBER_OF_COLOURS = 255;
  var ASPECT_RATIO = 16.0/9.0
  var HEIGHT = (WIDTH/ASPECT_RATIO).toInt
  var MAX_DEPTH = 20
  val INFINITY = 9999999.0
  var GAMMA = 0.5
  val EPSILON = 0.00001
}