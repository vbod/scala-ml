package ml.classification

import algebra.{DenseVector, Vector}
import ml.{Model, SupervisedEstimator}

class LogisticRegression extends SupervisedEstimator[LogisticRegressionModel] {
  override def train(data: Data): LogisticRegressionModel = {
    ???
  }
}

class LogisticRegressionModel(weights: DenseVector) extends Model {
  override type ResultType = Int

  override def applyUnitary(datum: Vector): ResultType = ???
}
