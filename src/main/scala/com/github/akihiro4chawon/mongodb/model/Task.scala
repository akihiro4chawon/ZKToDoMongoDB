package com.github.akihiro4chawon.mongodb.model

import java.util.Date
import java.util.UUID
import scala.reflect.BeanProperty

case class Task(
  @BeanProperty var id: String = null,
  @BeanProperty var name: String = null,
  @BeanProperty var priority: Int = 0,
  @BeanProperty var executionDate: Date = null)

