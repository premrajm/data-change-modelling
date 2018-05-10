package com.premrajm.model

import java.time.LocalDateTime

import com.premrajm.model.OperationType.OperationType

object OperationType extends Enumeration {
  type OperationType = Value
  val Insert, Update, Delete = Value
}

case class Operation(tableName: String, optType: OperationType)

case class Table(name: String, operation: Operation, timestamp: LocalDateTime)

case class DataChange(tables: List[Table]) {

  val operations: Set[Operation] = tables.map(t => t.operation).toSet

  def changes(operation: Operation) = tables.filter(o => o.operation == operation)

}
