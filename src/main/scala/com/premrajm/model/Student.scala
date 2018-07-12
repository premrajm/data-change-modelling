package com.premrajm.model

import java.util.UUID

import com.premrajm.model.Fields.absent

case class Student(id: String,
                   address: Field[String] = absent,
                   passed: Field[java.lang.Boolean] = AbsentBooleanField) {

  def attributes = List(
    Attribute("address", address),
    Attribute("passed", passed)
  )
}

object Test extends App {
  val student = Student(UUID.randomUUID().toString,
    StringField(Some("address")),
    BooleanField(Some(false)))


  val attributes = student.attributes
    .filter(_.field.isPresent)
    .map(a => a.name -> a.field.value)
    .toMap

  println(attributes)


}
