package com.github.akihiro4chawon.mongodb.model

import java.util.Date;

import org.bson.types.ObjectId

import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Id
import com.google.code.morphia.annotations.Indexed
import com.google.code.morphia.utils.IndexDirection

import scala.reflect.BeanProperty
import scala.annotation.target._

@Entity("tasks")
case class Task(
  @(Id @field) @BeanProperty
  var id: ObjectId = null,
  @(Indexed @field)(value = IndexDirection.ASC, name = "taskName", unique = true) @BeanProperty 
  var name: String = null,
  @(Indexed @field)(value = IndexDirection.ASC, name = "taskPriority") @BeanProperty
  var priority: Int = 0,
  @(Indexed @field)(value = IndexDirection.ASC, name = "executionDate") @BeanProperty
  var executionDate: Date = null) {
  def this() = this(id = null)
}
