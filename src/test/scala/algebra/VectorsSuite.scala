package algebra


class VectorsSuite extends FunSuite {

  test("basics") {
    val vec = DenseVector(Array(1d, 2d, 7d))
    assert(vec.dimension == 3)
    assert(vec(0) == 1d)
    assert(vec(1) == 2d)
    assert(vec(2) == 7d)
    assert(vec.iterator().toSeq == Seq((0, 1d), (1, 2d), (2, 7d)))
  }
}
