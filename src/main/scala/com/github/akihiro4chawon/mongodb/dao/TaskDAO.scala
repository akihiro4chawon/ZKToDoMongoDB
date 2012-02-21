package com.github.akihiro4chawon.mongodb.dao

import java.util.ArrayList
import java.util.Date
import java.util.{List => JList}

import com.github.akihiro4chawon.mongodb.MongoDBManager
import com.github.akihiro4chawon.mongodb.model.Task

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject

import scala.collection.JavaConverters._

class TaskDAO {
  import MyImplicits._
  
  def findAll(): JList[Task] = {
    val db = MongoDBManager.getDB("simpletasks")
    val coll = db.getCollection("Tasks")
    val cursor = coll.find()
    val tasks = for {
      dbobj <- cursor
      id = dbobj.get("id").asInstanceOf[String]
      name = dbobj.get("name").asInstanceOf[String]
      priority = dbobj.get("priority").asInstanceOf[Int]
      date = dbobj.get("date").asInstanceOf[Date]
    } yield Task(id, name, priority, date)
    tasks.toBuffer.asJava
  }
  
  def delete(task: Task) {
    val newdbobj = toBasicDBObject(task)
    val db = MongoDBManager.getDB("simpletasks")
    val coll = db.getCollection("Tasks")
    coll.remove(newdbobj)
  }
  
  private def toBasicDBObject(task: Task): BasicDBObject = {
    val newdbobj = new BasicDBObject()
    newdbobj.put("id", task.getId)
    newdbobj.put("name", task.getName)
    newdbobj.put("priority", task.getPriority)
    newdbobj.put("date", task.getExecutionDate)
    newdbobj
  }
  
  def insert(task: Task) {
    val newdbobj = toBasicDBObject(task)
    val db = MongoDBManager.getDB("simpletasks")
    val coll = db.getCollection("Tasks")
    coll.save(newdbobj)
  }
  
  def update(task: Task) {
    val searchobj = new BasicDBObject()
    searchobj.put("id", task.getId())
    val newdbobj = toBasicDBObject(task)
    val db = MongoDBManager.getDB("simpletasks")
    val coll = db.getCollection("Tasks")
    coll.update(searchobj, newdbobj)
  }
}

object MyImplicits {
  implicit def DBCursorToScalaIterator(cur: DBCursor): Iterator[DBObject] = new Iterator[DBObject] {
    def hasNext = cur.hasNext()
    def next = cur.next()
  }
}
