package ml


abstract class Model {

  type ResultType

  def apply(data: Seq[algebra.Vector]): Seq[ResultType] = {
    data.map(applyUnitary)
  }

  def applyUnitary(datum: algebra.Vector): ResultType
}
