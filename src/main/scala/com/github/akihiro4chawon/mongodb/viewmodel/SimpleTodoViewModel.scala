package com.github.akihiro4chawon.mongodb.viewmodel

import javax.annotation.Resource

import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.zk.ui.select.annotation.WireVariable

import scala.reflect.BeanProperty

import com.github.akihiro4chawon.mongodb.model.Task
import com.github.akihiro4chawon.mongodb.service.TaskService

class SimpleTodoViewModel {
  @WireVariable
  private var taskService: TaskService = _

  @BeanProperty var selectedTask: Task = _
  @BeanProperty var newTask = new Task()
  
  def getTasks = taskService.findAll()

  @Command(Array("add"))
  @NotifyChange(Array("tasks"))
  def add() {
    taskService.add(newTask)
    newTask = new Task()
  }
  
  @Command(Array("update"))
  @NotifyChange(Array("tasks"))
  def update() {
    taskService.update(selectedTask)
  }
  
  @Command(Array("delete"))
  @NotifyChange(Array("tasks", "selectedTask"))
  def delete() {
    if (selectedTask != null) {
      taskService.delete(selectedTask)
      selectedTask = null
    }
  }
}


