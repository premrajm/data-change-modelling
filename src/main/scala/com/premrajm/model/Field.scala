package com.premrajm.model

import java.time.LocalDate

sealed abstract class Field[T](option: Option[T]) {
  val isPresent: Boolean = true
  val value: Option[T] = option
}

case class BooleanField(override val value: Option[java.lang.Boolean]) extends Field[java.lang.Boolean](value)

case object AbsentBooleanField extends BooleanField(None)

case class StringField(override val value: Option[String]) extends Field[String](value)

case class NumberField(override val value: Option[Double]) extends Field[Double](value)

case class DateField(override val value: Option[LocalDate]) extends Field[LocalDate](value)

case class Absent[T]() extends Field[T](None) {
  override val isPresent = false
}

object Fields {
  def absent[T]: Absent[T] = Absent[T]()
}
