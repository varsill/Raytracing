import main.geometry._

class Vector3dTest extends org.scalatest.FunSuite {

  val v1 = new Vector3d(1,2,3)
  val v2 = new Vector3d(1,1,1)

  test("Vector3d add") {
    assert(v1 + v2 == new Vector3d(2,3,4))
  }

  test("Vector3d diff") {
    assert(v1 - v2 == new Vector3d(0,1,2))
  }

  test("neg") {
    assert(-v1 == new Vector3d(-1,-2,-3))
  }

  test("Vector3d mult") {
    assert(v1 * v2 == 1 + 2 + 3)
  }

  test("scalar mult") {
    assert(v1*2 == new Vector3d(2,4,6))
  }

  test("scalar div") {
    assert(v1/2 == new Vector3d(0.5, 1 , 1.5))
  }

}