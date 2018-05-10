package com.premrajm.model

sealed trait Criteria {
  def matches(dataChange: DataChange): Boolean
}

case class ExactMatch(operations: Set[Operation]) extends Criteria {

  def this(operation: Operation) = this(Set(operation))

  override def matches(dataChange: DataChange): Boolean = operations equals dataChange.operations
}

case class SubsetMatch(operations: Set[Operation]) extends Criteria {

  def this(operation: Operation) = this(Set(operation))

  if (operations.size <= 1) throw new IllegalArgumentException("Use ExactMatch variant")

  override def matches(dataChange: DataChange): Boolean = operations subsetOf dataChange.operations
}