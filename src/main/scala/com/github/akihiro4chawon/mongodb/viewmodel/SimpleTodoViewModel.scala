package com.github.akihiro4chawon.mongodb.viewmodel

import java.util.List
import java.util.UUID

import com.github.akihiro4chawon.mongodb.dao.TaskDAO
import com.github.akihiro4chawon.mongodb.model.Task

import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.NotifyChange

import scala.reflect.BeanProperty

class SimpleTodoViewModel {
  private val taskDao = new TaskDAO
  
  @BeanProperty var selectedTask: Task = _
  @BeanProperty var newTask = new Task()
  
  def getTasks = taskDao.findAll()

  @Command(Array("add"))
  @NotifyChange(Array("tasks"))
  def add() {
    newTask.setId(UUID.randomUUID().toString())
    taskDao.insert(newTask)
    newTask = new Task()
  }
  
  @Command(Array("update"))
  @NotifyChange(Array("tasks"))
  def update() {
    taskDao.update(selectedTask)
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

