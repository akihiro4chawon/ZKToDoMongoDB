package com.github.akihiro4chawon.mongodb.service

import java.util.{List => JList}

import javax.annotation.Resource

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.github.akihiro4chawon.mongodb.model.Task
import org.springframework.data.mongodb.core.query.Criteria.where

@Service("taskService")
@Transactional
class TaskService {
  @Resource(name="mongoTemplate")
  private var mongoTemplate: MongoTemplate = _
  
  def findAll() = {
    val query = new Query(where("id").exists(true))
    mongoTemplate.find(query, classOf[Task]): JList[Task]
  }
  
  def add(task: Task) {
    mongoTemplate insert task
  }
  
  def update(task: Task) {
    val query = new Query(where("id").is(task.getId()))
    val update = new Update
    update.set("name", task.getName)
    update.set("priority", task.getPriority())
    update.set("executionDate", task.getExecutionDate())
    mongoTemplate.updateMulti(query, update, classOf[Task])
  }
  
  def delete(task: Task) {
    val query = new Query(where("id").is(task.getId()))
    mongoTemplate.remove(query, classOf[Task])
  }
}
