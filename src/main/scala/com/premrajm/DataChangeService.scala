package com.premrajm

import java.time.LocalDateTime

import com.premrajm.handler.DataChangeHandler
import com.premrajm.model.{DataChange, Operation, OperationType, Table}

class DataChangeService(handlers: List[DataChangeHandler]) {

  val mappings = handlers.map(h => h.matchingCriteria -> h).toMap

  def process(dataChange: DataChange): Unit = {
    dataChange.tables
      .groupBy(t => t.operation)
      .toList
      .sortBy(tuple => tuple._1.optType.id)
      .map(tuple => mappings.get(tuple._1).map(h => h.handle(dataChange)))
      .filter(o => o.isDefined)
      .sortBy(e => e.get.timestamp)
      .foreach(e => println(e))
  }
}

object Test extends App{
  val ser = new DataChangeService(List())
  ser.process(DataChange(List(
    Table("abc", Operation("abc", OperationType.Update), LocalDateTime.now()),
    Table("def", Operation("def", OperationType.Insert), LocalDateTime.now()),
    Table("def", Operation("def", OperationType.Insert), LocalDateTime.now())
  )))
}
