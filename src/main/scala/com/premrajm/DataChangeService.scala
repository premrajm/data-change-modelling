package com.premrajm

import com.premrajm.handler.DataChangeHandler
import com.premrajm.model.DataChange

class DataChangeService(handlers: List[DataChangeHandler]) {

  val duplicates = handlers.groupBy(h => h.matchingCriteria).collect { case (x, List(_, _, _*)) => x }
  if (duplicates.nonEmpty) {
    throw new IllegalArgumentException(s"found duplicates : $duplicates")
  }

  def process(dataChange: DataChange): Unit = {
    handlers
      .find(h => h.matchingCriteria.matches(dataChange))
      .foreach(h => h.handle(dataChange))
  }
}
