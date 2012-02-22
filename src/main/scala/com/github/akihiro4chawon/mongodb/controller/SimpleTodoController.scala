package com.github.akihiro4chawon.mongodb.controller

import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

import javax.annotation.Resource

import org.springframework.context.annotation.Scope

import com.github.akihiro4chawon.mongodb.model.Task
import com.github.akihiro4chawon.mongodb.service.TaskService

import org.zkoss.zk.ui.event.Event
import org.zkoss.zk.ui.event.SelectEvent
import org.zkoss.zk.ui.util.GenericForwardComposer
import org.zkoss.zul.Datebox
import org.zkoss.zul.Intbox
import org.zkoss.zul.ListModelList
import org.zkoss.zul.Listbox
import org.zkoss.zul.Listcell
import org.zkoss.zul.Listitem
import org.zkoss.zul.ListitemRenderer
import org.zkoss.zul.Textbox
import org.zkoss.zul.Window

@org.springframework.stereotype.Component("todoCtrl")
@Scope("prototype")
class SimpleTodoController extends GenericForwardComposer[Window] {
  // autowired by ZK
  private var tasks: Listbox = _
  private var name: Textbox = _
  private var priority: Intbox = _
  private var date: Datebox = _

  @Resource(name="taskService")
  private var taskService: TaskService = _
  
  override def doAfterCompose(comp: Window) {
    super.doAfterCompose(comp)
    tasks.setModel(new ListModelList(taskService.findAll()))
    tasks.setItemRenderer(new ListitemRenderer[Task] {
      def render(item: Listitem, task: Task, index: Int) {
        val fmt = new SimpleDateFormat("yyyy-MM-dd")
        item.setValue(task)
        new Listcell(task.getName()).setParent(item)
        new Listcell("" + task.getPriority()).setParent(item)
        new Listcell(fmt format task.getExecutionDate).setParent(item)
      }
    })
  }
  
  def onSelect$tasks(evt: SelectEvent[Listitem, Task]) {
    val task = tasks.getSelectedItem.getValue[Task]
    name.setValue(task.getName)
    priority.setValue(task.getPriority)
    date.setValue(task.getExecutionDate)
  }
  
  def onClick$add(evt: Event) {
    val newTask = Task(
        UUID.randomUUID().toString,
        name.getValue,
        priority.getValue,
        date.getValue)    
    
    taskService.add(newTask)
    tasks.setModel(new ListModelList(taskService.findAll()))
  }
  
  def onClick$update() {
    val task = tasks.getSelectedItem.getValue[Task]
    task.setName(name.getValue.asInstanceOf[String])
    task.setPriority(priority.getValue.asInstanceOf[Int])
    task.setExecutionDate(date.getValue.asInstanceOf[Date])
    
    taskService.update(task)
    tasks.setModel(new ListModelList(taskService.findAll()))
  }
  
  def onClick$delete() {
    val task = tasks.getSelectedItem.getValue[Task]
    if (task != null) {
      taskService.delete(task)
      tasks.setModel(new ListModelList(taskService.findAll()))
    }
  }
}
