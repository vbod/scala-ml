package algebra

import org.scalatest.FunSuite


class VectorsSuite extends FunSuite {

  test("basics on dense") {
    val vec = DenseVector(Array(1d, 2d, 7d))
    assert(vec.dimension == 3)
    assert(vec(0) == 1d)
    assert(vec(1) == 2d)
    assert(vec(2) == 7d)
    assert(vec.iterator().toSeq == Seq((0, 1d), (1, 2d), (2, 7d)))
    assert(vec.iteratorOnActives().toSeq == Seq((0, 1d), (1, 2d), (2, 7d)))
  }

  test("basics on sparse") {
    val vec = SparseVector(5, Array(0, 1, 3), Array(1d, 2d, 7d))
    assert(vec.dimension == 5)
    assert(vec(0) == 1d)
    assert(vec(1) == 2d)
    assert(vec(2) == 0d)
    assert(vec(3) == 7d)
    assert(vec(4) == 0d)
    assert(vec.iterator().toSeq == Seq((0, 1d), (1, 2d), (2, 0d), (3, 7d), (4, 0d)))
    assert(vec.iteratorOnActives().toSeq == Seq((0, 1d), (1, 2d), (3, 7d)))

    // with unsorted data as input
    val vec2 = SparseVector(5, Array(0, 3, 1), Array(1d, 7d, 2d))
    assert(vec2.dimension == 5)
    assert(vec2(0) == 1d)
    assert(vec2(1) == 2d)
    assert(vec2(2) == 0d)
    assert(vec2(3) == 7d)
    assert(vec2(4) == 0d)
    assert(vec2.iterator().toSeq == Seq((0, 1d), (1, 2d), (2, 0d), (3, 7d), (4, 0d)))
    assert(vec2.iteratorOnActives().toSeq == Seq((0, 1d), (1, 2d), (3, 7d)))
  }
}
