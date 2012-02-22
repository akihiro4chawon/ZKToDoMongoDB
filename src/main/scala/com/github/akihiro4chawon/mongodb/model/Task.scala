package com.github.akihiro4chawon.mongodb.model

import java.util.Date;

import scala.reflect.BeanProperty

case class Task(
  @BeanProperty
  var id: String = null,
  @BeanProperty 
  var name: String = null,
  @BeanProperty
  var priority: Integer = 0,
  @BeanProperty
  var executionDate: Date = null) {
  def this() = this(id = null)
}
