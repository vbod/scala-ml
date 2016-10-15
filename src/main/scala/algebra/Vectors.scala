package algebra


trait Vector {
  def dimension: Int

  def apply(i: Int): Double

  def iterator(): Iterator[(Int, Double)]
}

case class DenseVector(private val data: Array[Double])
  extends Vector {

  override def dimension: Int = data.length

  override def apply(i: Int): Double = data(i)

  override def iterator(): Iterator[(Int, Double)] = new Iterator[(Int, Double)] {
    private var _count = 0

    override def hasNext: Boolean = _count < data.length

    override def next(): (Int, Double) = {
      val ret = (_count, data(_count))
      _count += 1
      ret
    }
  }
}

case class SparseVector(override val dimension: Int, private val indices: Array[Int], private val data: Array[Double])
  extends Vector {

  require(indices.length < dimension && (indices.length == data.length),
    "Indices and data should have the same length < dimension.")

  override def apply(i: Int): Double = {
    indices.find(_ == i) match {
      case Some(ii) => data(ii)
      case _ => 0d
    }
  }

  override def iterator(): Iterator[(Int, Double)] = new Iterator[(Int, Double)] {
    private var _count = 0

    override def hasNext: Boolean = _count < data.length

    override def next(): (Int, Double) = {
      val ret = (indices(_count), data(_count))
      _count += 1
      ret
    }
  }
}