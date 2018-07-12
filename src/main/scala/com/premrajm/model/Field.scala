package com.premrajm.model

import java.time.LocalDate

sealed abstract class Field[T](value: Option[T]) {
  val isPresent: Boolean = true
  val get: Option[T] = value
}

case class BooleanField(value: Option[java.lang.Boolean]) extends Field[java.lang.Boolean](value)
case object AbsentBooleanField extends BooleanField(None)

case class StringField(value: Option[String]) extends Field[String](value)
case class NumberField(value: Option[Double]) extends Field[Double](value)
case class DateField(value: Option[LocalDate]) extends Field[LocalDate](value)

case class Absent[T]() extends Field[T](None){
  override val isPresent = false
}

object Fields{
  def absent[T]: Absent[T] = Absent[T]()
}
