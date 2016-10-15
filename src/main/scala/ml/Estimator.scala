package ml


abstract class Estimator[M <: Model] {

  type Data

  def train(data: Data): M
}

abstract class SupervisedEstimator[M <: Model] extends Estimator {
  override type Data = Seq[LabeledData]
}

abstract class UnsupervisedEstimator[M <: Model] extends Estimator {
  override type Data = Seq[algebra.Vector]
}