package algebra


trait Vector {
  def dimension: Int

  def apply(i: Int): Double

  def iterator(): Iterator[(Int, Double)]

  def iteratorOnActives(): Iterator[(Int, Double)]
}

class DenseVector private(private val data: Seq[Double])
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

  override def iteratorOnActives(): Iterator[(Int, Double)] = iterator()
}

object DenseVector {
  def apply(data: Array[Double]): DenseVector = {
    new DenseVector(data)
  }
}

class SparseVector private(override val dimension: Int,
                           private val indices: Seq[Int],
                           private val data: Seq[Double])
  extends Vector {

  override def apply(i: Int): Double = {
    indices.indexOf(i) match {
      case ii if ii >= 0 => data(ii)
      case _ => 0d
    }
  }

  override def iterator(): Iterator[(Int, Double)] = new Iterator[(Int, Double)] {
    private var _count = 0

    override def hasNext: Boolean = _count < dimension

    override def next(): (Int, Double) = {
      val ret = (_count, apply(_count))
      _count += 1
      ret
    }
  }

  override def iteratorOnActives(): Iterator[(Int, Double)] = new Iterator[(Int, Double)] {
    private var _count = 0

    override def hasNext: Boolean = _count < data.length

    override def next(): (Int, Double) = {
      val ret = (indices(_count), data(_count))
      _count += 1
      ret
    }
  }
}

object SparseVector {
  def apply(dimension: Int, indices: Array[Int], data: Array[Double]): SparseVector = {
    require(indices.length < dimension && (indices.length == data.length),
      "Indices and data should have the same length < dimension.")
    val sorted = indices.zip(data).sortBy(_._1)
    new SparseVector(dimension, sorted.map(_._1), sorted.map(_._2))
  }
}