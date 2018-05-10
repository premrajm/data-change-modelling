package com.premrajm.handler

import com.premrajm.model.{Criteria, DataChange}


trait DataChangeHandler {

  def matchingCriteria: Criteria

  def handle(dataChange: DataChange): Unit
}
