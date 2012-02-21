package com.github.akihiro4chawon.mongodb.viewmodel

import java.util.List
import java.util.UUID

import com.github.akihiro4chawon.mongodb.dao.TaskDAO
import com.github.akihiro4chawon.mongodb.model.Task
import com.github.akihiro4chawon.mongodb.MongoDBManager

import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.NotifyChange

import scala.reflect.BeanProperty

class SimpleTodoViewModel {
  private val taskDao = new TaskDAO(MongoDBManager.getMongo(), MongoDBManager.getMorphia())
  
  @BeanProperty var selectedTask: Task = _
  @BeanProperty var newTask = new Task()
  
  def getTasks = taskDao.find.asList

  @Command(Array("add"))
  @NotifyChange(Array("tasks"))
  def add() {
    taskDao.save(newTask)
    newTask = new Task()
  }
  
  @Command(Array("update"))
  @NotifyChange(Array("tasks"))
  def update() {
    taskDao.save(selectedTask)
  }
  
  @Command(Array("delete"))
  @NotifyChange(Array("tasks", "selectedTask"))
  def delete() {
    if (selectedTask != null) {
      taskDao.delete(selectedTask)
      selectedTask = null
    }
  }
}

