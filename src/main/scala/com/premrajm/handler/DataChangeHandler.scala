package com.premrajm.handler

import com.premrajm.model.{DataChange, Operation}


trait DataChangeHandler {

  def matchingCriteria: Operation

  def handle(dataChange: DataChange): Unit
}
