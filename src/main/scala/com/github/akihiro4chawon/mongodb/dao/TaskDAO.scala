package com.github.akihiro4chawon.mongodb.dao

import org.bson.types.ObjectId

import com.github.akihiro4chawon.mongodb.model.Task

import com.google.code.morphia.Morphia
import com.google.code.morphia.dao.BasicDAO
import com.mongodb.Mongo

class TaskDAO(mongo: Mongo, morphia: Morphia)
  extends BasicDAO[Task, ObjectId](mongo, morphia, "simpletodo")
