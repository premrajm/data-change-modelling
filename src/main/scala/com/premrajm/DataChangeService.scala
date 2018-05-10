package com.premrajm

import com.premrajm.handler.DataChangeHandler
import com.premrajm.model.DataChange

class DataChangeService(handlers: List[DataChangeHandler]) {

  val mappings = handlers.map(h => h.matchingCriteria -> h).toMap

  def process(dataChange: DataChange): Unit = {
    dataChange.tables
      .groupBy(t => t.operation)
      .toList
      .sortBy(tuple => tuple._1.optType.id)
      .foreach(tuple => mappings.get(tuple._1).foreach(h => h.handle(dataChange)))
  }
}
