package com.premrajm.handler

import java.time.LocalDateTime

import com.premrajm.model.{DataChange, Operation}

case class Event(data: String, timestamp: LocalDateTime)

trait DataChangeHandler {

  def matchingCriteria: Operation

  def handle(dataChange: DataChange): Event
}
